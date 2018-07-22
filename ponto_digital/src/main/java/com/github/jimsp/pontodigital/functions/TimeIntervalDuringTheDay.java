package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeIntervalDuringTheDay implements BiFunction<Employer, ItsTheSameDay, Integer> {

	public static TimeIntervalDuringTheDay create() {
		return new TimeIntervalDuringTheDay();
	}

	private final Interval interval = Interval.create();
	private final MillisecondsConversion millisecondsToMinutes = MillisecondsConversion.createToSeconds();
	private final DateToMilliseconds dateToMilliseconds = DateToMilliseconds.create();
	private final TimeWorkedDuringTheDay timeWorkedDuringTheDay = TimeWorkedDuringTheDay.create();

	private TimeIntervalDuringTheDay() {

	}

	@Override
	public Integer apply(final Employer employer, final ItsTheSameDay itsTheSameDay) {

		final Integer work = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		final Integer jorney = millisecondsToMinutes //
				.apply( //
						interval.apply( //
								dateToMilliseconds //
										.apply( //
												getFirstEntrieDay(employer.getEntries(), itsTheSameDay)), //
								//
								dateToMilliseconds //
										.apply( //
												getLastEntrieDay(employer.getEntries(), itsTheSameDay))));
		return jorney - work;
	}

	private Date getFirstEntrieDay(final List<String> entries, final ItsTheSameDay itsTheSameDay) {
		return entries //
				.stream() //
				.map(DateFormat.parseDate()) //
				.filter(itsTheSameDay) //
				.reduce((a, b) -> a.before(b) ? a : b) //
				.get();
	}

	private Date getLastEntrieDay(final List<String> entries, final ItsTheSameDay itsTheSameDay) {
		return entries //
				.stream() //
				.map(DateFormat.parseDate()) //
				.filter(itsTheSameDay) //
				.reduce((a, b) -> a.before(b) ? b : a) //
				.get();
	}
}
