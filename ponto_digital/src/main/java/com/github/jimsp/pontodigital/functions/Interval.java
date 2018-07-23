package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public final class Interval implements BinaryOperator<Long>{
	
	public static final Interval create() {
		return new Interval();
	}
	
	private final BiFunction<Long, Long, Long> subtraction = $().subtraction();
	
	private Interval() {
		
	}
	
	@Override
	public Long apply(final Long begin, final Long end) {
		return subtraction.apply(end, begin);
	}
}
