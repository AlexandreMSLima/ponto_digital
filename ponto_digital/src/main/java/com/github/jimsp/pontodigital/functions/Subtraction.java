package com.github.jimsp.pontodigital.functions;

import java.util.function.BiFunction;

public final class Subtraction implements BiFunction<Long, Long, Long>{

	public static Subtraction create() {
		return new Subtraction();
	}
	
	private Subtraction() {
		
	}
	
	@Override
	public Long apply(final Long valueA, final Long valueB) {
		return valueA - valueB;
	}

}
