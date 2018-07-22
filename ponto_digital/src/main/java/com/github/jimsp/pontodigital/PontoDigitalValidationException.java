package com.github.jimsp.pontodigital;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.github.jimsp.pontodigital.dto.PontoDigitalDto;

public class PontoDigitalValidationException extends RuntimeException {

	private static final long serialVersionUID = 5616140609179533274L;
	
	private final Set<ConstraintViolation<PontoDigitalDto>> constraintViolationSet;
	
	public PontoDigitalValidationException(final Set<ConstraintViolation<PontoDigitalDto>> constraintViolationSet) {
		this.constraintViolationSet = constraintViolationSet;
	}

	public Set<ConstraintViolation<PontoDigitalDto>> getConstraintViolationSet() {
		return constraintViolationSet;
	}

	@Override
	public String toString() {
		return "PontoDigitalValidationException [constraintViolationSet=" + Arrays.asList(constraintViolationSet) + "]";
	}
}
