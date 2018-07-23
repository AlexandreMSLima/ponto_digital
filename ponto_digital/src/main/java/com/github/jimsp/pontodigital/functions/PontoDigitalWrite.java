package com.github.jimsp.pontodigital.functions;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalWithProblemFileException;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public final class PontoDigitalWrite implements BiConsumer<OutputStream, List<PontoDigitalReport>> {

	public static PontoDigitalWrite create(final ObjectMapper objectMapper) {
		return new PontoDigitalWrite(objectMapper);
	}
	
	public static PontoDigitalWrite create() {
		final ObjectMapper objectMapper = new ObjectMapper();
		return new PontoDigitalWrite(objectMapper);
	}

	private final ObjectMapper objectMapper;

	private PontoDigitalWrite(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void accept(final OutputStream output, final List<PontoDigitalReport> report) {
		try {
			output.write(objectMapper.writeValueAsBytes(report));
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}
}
