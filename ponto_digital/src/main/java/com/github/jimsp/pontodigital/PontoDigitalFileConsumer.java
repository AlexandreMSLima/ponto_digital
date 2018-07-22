package com.github.jimsp.pontodigital;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;

public final class PontoDigitalFileConsumer implements BiConsumer<String, String> {

	public static PontoDigitalFileConsumer create(final PontoDigitalFluxe pontoDigitalFluxe,
			final PontoDigitalIO pontoDigitalIO) {
		final ObjectMapper objectMapper = new ObjectMapper();
		return new PontoDigitalFileConsumer(objectMapper, pontoDigitalFluxe, pontoDigitalIO);
	}
	
	public static PontoDigitalFileConsumer create(final ObjectMapper objectMapper, final PontoDigitalFluxe pontoDigitalFluxe,
			final PontoDigitalIO pontoDigitalIO) {
		return new PontoDigitalFileConsumer(objectMapper, pontoDigitalFluxe, pontoDigitalIO);
	}

	private final ObjectMapper objectMapper;
	private final PontoDigitalFluxe pontoDigitalFluxe;
	private final PontoDigitalIO pontoDigitalIO;

	private PontoDigitalFileConsumer(final ObjectMapper objectMapper, final PontoDigitalFluxe pontoDigitalFluxe,
			final PontoDigitalIO pontoDigitalIO) {
		this.objectMapper = objectMapper;
		this.pontoDigitalFluxe = pontoDigitalFluxe;
		this.pontoDigitalIO = pontoDigitalIO;
	}

	@Override
	public void accept(@NotBlank final String input, @NotBlank final String output) {
		try {
			final PontoDigitalDto pontoDigitalDto = read(input);
			final List<PontoDigitalReport> report = process(pontoDigitalDto);
			write(output, report);
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}

	public void write(final String output, final List<PontoDigitalReport> report)
			throws IOException, JsonProcessingException {
		Files.write(Paths.get(pontoDigitalIO.getOutput().getAbsolutePath() + File.separatorChar + output),
				objectMapper.writeValueAsBytes(report));
	}

	public List<PontoDigitalReport> process(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalFluxe.processDto(pontoDigitalDto);
	}

	public PontoDigitalDto read(final String input) throws IOException, JsonParseException, JsonMappingException {
		return objectMapper.readValue(
				Files.readAllBytes(Paths.get(pontoDigitalIO.getInput().getAbsolutePath() + File.separatorChar + input)),
				PontoDigitalDto.class);
	}
}
