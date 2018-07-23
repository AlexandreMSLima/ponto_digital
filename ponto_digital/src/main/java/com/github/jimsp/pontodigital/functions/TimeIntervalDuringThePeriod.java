package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringThePeriod implements Function<Employer, Integer> {

	public static TimeIntervalDuringThePeriod create() {
		return new TimeIntervalDuringThePeriod();
	}
	
	private final BinaryOperator<Long> interval = Interval.create();
	private final Function<Long, Integer> millisecondsToMinutes = $().millisecondsToSecondsConversion();
	private final Function<Date, Long> dateToMilliseconds = $().dateToMilliseconds();
	private final Function<Employer, Integer> timeWorkedDuringThePeriod = $().timeWorkedDuringThePeriod();

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
