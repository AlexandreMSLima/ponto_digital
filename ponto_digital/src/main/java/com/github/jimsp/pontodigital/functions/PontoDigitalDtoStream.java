package com.github.jimsp.pontodigital.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.FunctionalCatalog;
import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.wrapper.PeriudWorkParam;

public final class PontoDigitalDtoStream {

	private static final Function<Long, Integer> millisecondsConversion = FunctionalCatalog.millisecondsToSecondsConversion();
	private static final BinaryOperator<Long> interval = FunctionalCatalog.interval();

	public static Stream<Employer> of(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalDto //
				.getEmployees() //
				.stream();
	}

	public static Stream<PeriudWorkParam> of(final Employer employer) {
		final List<Date> entries = employer //
				.getEntries() //
				.stream() //
				.sorted() //
				.map(DateFormat.parseDateTime()) //
				.collect(Collectors.toList());

		final List<PeriudWorkParam> periudWorkParams = new ArrayList<>();
		int i = 0;
		while (i < entries.size()) {
			final Date entry = entries.get(i);
			final Date exit = entries.get(i + 1);
			final Predicate<Date> itsTheSameDay = FunctionalCatalog.itsSameDay(entry);

			if (itsTheSameDay.test(exit)) {

				final Integer timeWorkMinutes = millisecondsConversion
						.apply(interval.apply(entry.getTime(), exit.getTime()));

				periudWorkParams //
						.add( //
								PeriudWorkParam //
										.builder() //
										.entry(entry) //
										.exit(exit) //
										.timeWorkMinutes(timeWorkMinutes) //
										.build());
			} else {
				final Integer timeWorkMinutesAtLast = millisecondsConversion //
						.apply(interval //
								.apply(entry.getTime(), //
										DateFormat.parseTimeLastHour() //
												.apply(entry) //
												.getTime()));

				final Integer timeWorkMinutesOfZeroHour = millisecondsConversion //
						.apply(interval //
								.apply(DateFormat.parseTimeZeroHour() //
										.apply(exit) //
										.getTime(), //
										exit.getTime()));

				periudWorkParams //
						.add( //
								PeriudWorkParam //
										.builder() //
										.entry(entry) //
										.exit(DateFormat.parseTimeLastHour().apply(entry)) //
										.timeWorkMinutes(timeWorkMinutesAtLast) //
										.build());
				periudWorkParams //
						.add( //
								PeriudWorkParam //
										.builder() //
										.entry(DateFormat.parseTimeZeroHour().apply(exit)) //
										.exit(exit) //
										.timeWorkMinutes(timeWorkMinutesOfZeroHour) //
										.build());
			}

			i = i + 2;
		}

		return periudWorkParams //
				.stream();
	}
}
