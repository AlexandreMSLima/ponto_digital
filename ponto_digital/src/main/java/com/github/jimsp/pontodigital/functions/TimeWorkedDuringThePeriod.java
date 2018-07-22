package com.github.jimsp.pontodigital.functions;

import java.util.function.Function;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeWorkedDuringThePeriod implements Function<Employer, Integer>{
	
	public static TimeWorkedDuringThePeriod create() {
		return new TimeWorkedDuringThePeriod();
	}
	
	private final Interval interval = Interval.create();
	private final MillisecondsConversion millisecondsToMinutes = MillisecondsConversion.createToSeconds();
	private final DateToMilliseconds dateToMilliseconds = DateToMilliseconds.create();

	private TimeWorkedDuringThePeriod() {
		
	}
	
	public Integer apply(final Employer employer) {
		return PontoDigitalDtoStream
				.of(employer)
				 //
			.map(dateToMilliseconds) //
			.reduce(interval) //
			.map(millisecondsToMinutes) //
			.orElse(0);
	}

}
