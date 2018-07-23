package com.github.jimsp.pontodigital.functions;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.github.jimsp.pontodigital.Context;

public final class Interval implements BinaryOperator<Long>{
	
	public static final Interval create() {
		return new Interval();
	}
	
	private final BiFunction<Long, Long, Long> subtraction = Context.subtraction();
	
	private Interval() {
		
	}
	
	@Override
	public Long apply(final Long begin, final Long end) {
		return subtraction.apply(end, begin);
	}
}
