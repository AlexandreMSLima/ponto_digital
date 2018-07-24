package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringTheDay implements BiFunction<Employer, Predicate<Date>, Integer> {

	public static TimeIntervalDuringTheDay create(final BinaryOperator<Long> interval,
			final Function<Long, Integer> millisecondsToMinutes, final Function<Date, Long> dateToMilliseconds,
			final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay,
			final BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay,
			final BiFunction<List<String>, Predicate<Date>, Date> lastEntrieDay) {
		return new TimeIntervalDuringTheDay(interval, millisecondsToMinutes, dateToMilliseconds, timeWorkedDuringTheDay,
				firstEntrieDay, lastEntrieDay);
	}

	private final BinaryOperator<Long> interval;
	private final Function<Long, Integer> millisecondsToMinutes;
	private final Function<Date, Long> dateToMilliseconds;
	private final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay;
	private final BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay;
	private final BiFunction<List<String>, Predicate<Date>, Date> lastEntrieDay;

	private TimeIntervalDuringTheDay(final BinaryOperator<Long> interval,
			final Function<Long, Integer> millisecondsToMinutes,
			final Function<Date, Long> dateToMilliseconds,
			final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay,
			final BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay,
			final BiFunction<List<String>, Predicate<Date>, Date> lastEntrieDay) {
		this.interval = interval;
		this.millisecondsToMinutes = millisecondsToMinutes;
		this.dateToMilliseconds = dateToMilliseconds;
		this.timeWorkedDuringTheDay = timeWorkedDuringTheDay;
		this.firstEntrieDay = firstEntrieDay;
		this.lastEntrieDay = lastEntrieDay;
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
