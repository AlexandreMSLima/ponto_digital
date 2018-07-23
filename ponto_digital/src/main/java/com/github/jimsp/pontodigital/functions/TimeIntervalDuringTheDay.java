package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringTheDay implements BiFunction<Employer, Predicate<Date>, Integer> {

	public static TimeIntervalDuringTheDay create() {
		return new TimeIntervalDuringTheDay();
	}

	private final BinaryOperator<Long> interval = Interval.create();
	private final Function<Long, Integer> millisecondsToMinutes = MillisecondsConversion.createToSeconds();
	private final Function<Date, Long> dateToMilliseconds = DateToMilliseconds.create();
	private final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay = TimeWorkedDuringTheDay.create();
	private final BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay = FirstEntrieDay.create();
	private final BiFunction<List<String>, Predicate<Date>, Date> lastEntrieDay = LastEntrieDay.create();

	private TimeIntervalDuringTheDay() {

	}

	@Override
	public Integer apply(final Employer employer, final Predicate<Date> itsTheSameDay) {
		final Integer work = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		final Integer jorney = millisecondsToMinutes //
				.apply( //
						interval.apply( //
								dateToMilliseconds //
										.apply( //
												firstEntrieDay.apply(employer.getEntries(), itsTheSameDay)), //
								//
								dateToMilliseconds //
										.apply( //
												lastEntrieDay.apply(employer.getEntries(), itsTheSameDay))));
		return jorney - work;
	}
}
