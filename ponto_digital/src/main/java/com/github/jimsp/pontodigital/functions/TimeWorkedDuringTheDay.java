package com.github.jimsp.pontodigital.functions;

import java.util.function.BiFunction;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeWorkedDuringTheDay implements BiFunction<Employer, ItsTheSameDay, Integer> {

	public static TimeWorkedDuringTheDay create() {
		return new TimeWorkedDuringTheDay();
	}

	private final Interval interval = Interval.create();
	private final MillisecondsConversion millisecondsToMinutes = MillisecondsConversion.createToSeconds();
	private final DateToMilliseconds dateToMilliseconds = DateToMilliseconds.create();

	private TimeWorkedDuringTheDay() {

	}

	@Override
	public Integer apply(final Employer employer, final ItsTheSameDay itsTheSameDay) {

		return PontoDigitalDtoStream.of(employer)
				//
				.filter(itsTheSameDay).map(dateToMilliseconds) //
				.reduce(interval) //
				.map(millisecondsToMinutes) //
				.orElse(0);
	}
}
