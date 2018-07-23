package com.github.jimsp.pontodigital.functions;

import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalValidationException;

public final class PontoDigitalValidate implements Consumer<PontoDigitalDto>{
	
	public static PontoDigitalValidate create(final Validator validator) {
		return new PontoDigitalValidate(validator);
	}
	
	public static PontoDigitalValidate create() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		return new PontoDigitalValidate(validator);
	}
	
	private PontoDigitalValidate(final Validator validator) {
		this.validator = validator;
	}
	
	private final Validator validator;
	
	public void accept(final PontoDigitalDto pontoDigitalDto) {
		final Set<ConstraintViolation<PontoDigitalDto>> constraintViolationSet = validator.validate(pontoDigitalDto);
		if(!constraintViolationSet.isEmpty()) {
			throw new PontoDigitalValidationException(constraintViolationSet);
		}
	}

}
