package com.github.jimsp.pontodigital.functions;

import java.util.function.Function;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringThePeriod implements Function<Employer, Integer> {

	public static TimeIntervalDuringThePeriod create() {
		return new TimeIntervalDuringThePeriod();
	}
	
	private final Interval interval = Interval.create();
	private final MillisecondsConversion millisecondsToMinutes = MillisecondsConversion.createToSeconds();
	private final DateToMilliseconds dateToMilliseconds = DateToMilliseconds.create();
	private final TimeWorkedDuringThePeriod timeWorkedDuringThePeriod = TimeWorkedDuringThePeriod.create();

	private TimeIntervalDuringThePeriod() {
		
	}
	
	@Override
	public Integer apply(final Employer employer) {
		final Integer work = timeWorkedDuringThePeriod.apply(employer);
		final Integer jorney = millisecondsToMinutes //
				.apply(interval.apply( //
						dateToMilliseconds.apply(DateFormat.parseDateTime().apply(employer.getEntries().get(0))), //
						dateToMilliseconds.apply(DateFormat.parseDateTime()
								.apply(employer.getEntries().get(employer.getEntries().size() - 1)))));
		return jorney - work;
	}
}
