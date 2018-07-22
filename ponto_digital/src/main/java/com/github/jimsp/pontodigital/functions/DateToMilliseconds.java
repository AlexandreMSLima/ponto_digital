package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.function.Function;

public final class DateToMilliseconds implements Function<Date, Long> {
	
	public static final DateToMilliseconds create() {
		return new DateToMilliseconds();
	}
	
	private DateToMilliseconds() {
	
	}

	@Override
	public Long apply(final Date dateTime) {
		return dateTime.getTime();
	}
}
