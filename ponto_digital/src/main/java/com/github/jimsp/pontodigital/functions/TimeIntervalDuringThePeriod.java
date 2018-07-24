package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringThePeriod implements Function<Employer, Integer> {

	public static TimeIntervalDuringThePeriod create(final BinaryOperator<Long> interval,
			final Function<Long, Integer> millisecondsToMinutes, final Function<Date, Long> dateToMilliseconds,
			final Function<Employer, Integer> timeWorkedDuringThePeriod) {
		return new TimeIntervalDuringThePeriod(interval, millisecondsToMinutes, dateToMilliseconds,
				timeWorkedDuringThePeriod);
	}

	private final BinaryOperator<Long> interval;
	private final Function<Long, Integer> millisecondsToMinutes;
	private final Function<Date, Long> dateToMilliseconds;
	private final Function<Employer, Integer> timeWorkedDuringThePeriod;

	private TimeIntervalDuringThePeriod(final BinaryOperator<Long> interval,
			final Function<Long, Integer> millisecondsToMinutes, final Function<Date, Long> dateToMilliseconds,
			final Function<Employer, Integer> timeWorkedDuringThePeriod) {
		this.interval = interval;
		this.millisecondsToMinutes = millisecondsToMinutes;
		this.dateToMilliseconds = dateToMilliseconds;
		this.timeWorkedDuringThePeriod = timeWorkedDuringThePeriod;
	}

	@Override
	public Integer apply(final Employer employer) {
		final Integer work = timeWorkedDuringThePeriod.apply(employer);
		final Integer jorney = millisecondsToMinutes //
				.apply(interval.apply( //
						dateToMilliseconds.apply($.parseDateTime().apply(employer.getEntries().get(0))), //
						dateToMilliseconds.apply($.parseDateTime()
								.apply(employer.getEntries().get(employer.getEntries().size() - 1)))));
		return jorney - work;
	}
}
