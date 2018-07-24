package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class FirstEntrieDay implements BiFunction<List<String>, Predicate<Date>, Date> {

	public static FirstEntrieDay create() {
		return new FirstEntrieDay();
	}

	private FirstEntrieDay() {

	}

	@Override
	public Date apply(final List<String> entries, final Predicate<Date> itsTheSameDay) {
		return entries //
				.stream() //
				.map($.parseDateTime()) //
				.filter(itsTheSameDay) //
				.reduce((a, b) -> a.before(b) ? a : b) //
				.get();
	}
}
