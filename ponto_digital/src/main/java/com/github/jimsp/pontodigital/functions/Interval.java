package com.github.jimsp.pontodigital.functions;

import java.util.function.BinaryOperator;

public final class Interval implements BinaryOperator<Long>{
	
	public static final Interval create() {
		return new Interval();
	}
	
	private final Subtraction subtraction = Subtraction.create();
	
	private Interval() {
		
	}
	
	@Override
	public Long apply(final Long begin, final Long end) {
		return subtraction.apply(end, begin);
	}
}
