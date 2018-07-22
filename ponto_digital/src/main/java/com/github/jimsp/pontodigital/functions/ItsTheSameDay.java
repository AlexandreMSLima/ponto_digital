package com.github.jimsp.pontodigital.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public final class ItsTheSameDay implements Predicate<Date> {

	public static ItsTheSameDay create(final Date day) {
		return new ItsTheSameDay(day);
	}

	private final Date day;

	private ItsTheSameDay(final Date day) {
		this.day = day;
	}

	@Override
	public boolean test(final Date localDateTime) {
		return new SimpleDateFormat("yyyyMMdd") //
				.format(day) //
				.equals(new SimpleDateFormat("yyyyMMdd") //
						.format(localDateTime));
	}

}
