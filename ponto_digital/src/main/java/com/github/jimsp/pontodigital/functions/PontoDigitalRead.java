package com.github.jimsp.pontodigital.functions;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalWithProblemFileException;

public final class PontoDigitalRead implements Function<InputStream, PontoDigitalDto>{
	
	public static PontoDigitalRead create(final ObjectMapper objectMapper, final Consumer<PontoDigitalDto> pontoDigitalValidate) {
		return new PontoDigitalRead(objectMapper, pontoDigitalValidate);
	}
	
	public static PontoDigitalRead create() {
		return new PontoDigitalRead(new ObjectMapper(), PontoDigitalValidate.create());
	}
	
	private final ObjectMapper objectMapper;
	private final Consumer<PontoDigitalDto> pontoDigitalValidate;
	
	private PontoDigitalRead(final ObjectMapper objectMapper, final Consumer<PontoDigitalDto> pontoDigitalValidate) {
		this.objectMapper = objectMapper;
		this.pontoDigitalValidate = pontoDigitalValidate;
	}
	
	public PontoDigitalDto apply(final InputStream input) {
		PontoDigitalDto pontoDigitalDto;
		try {
			pontoDigitalDto = objectMapper.readValue(
					input,
					PontoDigitalDto.class);
			
			pontoDigitalValidate.accept(pontoDigitalDto);
			
			return pontoDigitalDto;
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}

}
