package com.github.jimsp.pontodigital;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalValidationException;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalWithProblemFileException;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public final class PontoDigitalFileConsumer implements BiConsumer<InputStream, OutputStream> {

	public static PontoDigitalFileConsumer create(final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		final ObjectMapper objectMapper = new ObjectMapper();
		return new PontoDigitalFileConsumer(objectMapper, pontoDigitalFluxe);
	}
	
	public static PontoDigitalFileConsumer create(final ObjectMapper objectMapper, final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		return new PontoDigitalFileConsumer(objectMapper, pontoDigitalFluxe);
	}

	private final ObjectMapper objectMapper;
	private final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe;

	private PontoDigitalFileConsumer(final ObjectMapper objectMapper, final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		this.objectMapper = objectMapper;
		this.pontoDigitalFluxe = pontoDigitalFluxe;
	}

	@Override
	public void accept(@NotNull final InputStream input, @NotNull final OutputStream output) {
		try {
			final PontoDigitalDto pontoDigitalDto = read(input);
			final List<PontoDigitalReport> report = process(pontoDigitalDto);
			write(output, report);
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}

	public void write(final OutputStream output, final List<PontoDigitalReport> report)
			throws IOException, JsonProcessingException {
		output.write(objectMapper.writeValueAsBytes(report));
	}

	public List<PontoDigitalReport> process(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalFluxe.apply(pontoDigitalDto);
	}

	public PontoDigitalDto read(final InputStream input) throws IOException, JsonParseException, JsonMappingException {
		final PontoDigitalDto pontoDigitalDto = objectMapper.readValue(
				input,
				PontoDigitalDto.class);
		validate(pontoDigitalDto);
		
		return pontoDigitalDto;
	}

	private void validate(final PontoDigitalDto pontoDigitalDto) {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		
		final Set<ConstraintViolation<PontoDigitalDto>> constraintViolationSet = validator.validate(pontoDigitalDto);
		if(!constraintViolationSet.isEmpty()) {
			throw new PontoDigitalValidationException(constraintViolationSet);
		}
	}
}
