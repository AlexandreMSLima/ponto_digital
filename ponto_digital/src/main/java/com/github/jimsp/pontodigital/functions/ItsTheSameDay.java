package com.github.jimsp.pontodigital.functions;

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
		return DateFormat //
				.formartDate() //
				.apply(day) //
				.equals(DateFormat //
						.formartDate() //
						.apply(localDateTime));
	}

}
