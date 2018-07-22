package com.github.jimsp.pontodigital.functions;

import java.math.BigInteger;
import java.util.function.Function;

public final class MillisecondsConversion implements Function<Long, Integer> {
	
	public static MillisecondsConversion createToSeconds() {
		return new MillisecondsConversion(F_MILI_TO_MIN);
	}

	public static final Integer F_MILI_TO_MIN = 1_000 * 60;
	
	private final Integer factorTime;
	
	private MillisecondsConversion(final Integer factorTime) {
		this.factorTime = factorTime;
	}

	@Override
	public Integer apply(final Long timeMiliseconds) {
		return BigInteger //
				.valueOf(timeMiliseconds / factorTime) //
				.intValue();
	}
}
