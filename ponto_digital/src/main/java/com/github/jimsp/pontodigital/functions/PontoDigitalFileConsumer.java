package com.github.jimsp.pontodigital.functions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public final class PontoDigitalFileConsumer implements BiConsumer<InputStream, OutputStream> {

	public static PontoDigitalFileConsumer create(
			final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		return new PontoDigitalFileConsumer(objectMapper, validator, pontoDigitalFluxe);
	}

	public static PontoDigitalFileConsumer create(final ObjectMapper objectMapper, final Validator validator,
			final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		return new PontoDigitalFileConsumer(objectMapper, validator, pontoDigitalFluxe);
	}

	private final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe;
	private final Function<InputStream, PontoDigitalDto> pontoDigitalRead;
	private final BiConsumer<OutputStream, List<PontoDigitalReport>> pontoDigitalWrite;

	private PontoDigitalFileConsumer(final ObjectMapper objectMapper, final Validator validator,
			final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe) {
		this.pontoDigitalFluxe = pontoDigitalFluxe;
		this.pontoDigitalRead = PontoDigitalRead.create(objectMapper, PontoDigitalValidate.create(validator));
		this.pontoDigitalWrite = PontoDigitalWrite.create(objectMapper);
	}

	@Override
	public void accept(@NotNull final InputStream input, @NotNull final OutputStream output) {
		final PontoDigitalDto pontoDigitalDto = pontoDigitalRead.apply(input);
		final List<PontoDigitalReport> report = pontoDigitalFluxe.apply(pontoDigitalDto);
		pontoDigitalWrite.accept(output, report);
	}
}
