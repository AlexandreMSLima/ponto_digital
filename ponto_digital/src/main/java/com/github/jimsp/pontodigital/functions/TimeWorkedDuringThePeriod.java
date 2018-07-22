package com.github.jimsp.pontodigital.functions;

import java.util.function.Function;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeWorkedDuringThePeriod implements Function<Employer, Integer>{
	
	public static TimeWorkedDuringThePeriod create() {
		return new TimeWorkedDuringThePeriod();
	}

	private TimeWorkedDuringThePeriod() {
		
	}
	
	public Integer apply(final Employer employer) {
		return PontoDigitalDtoStream //
				.of(employer) //
				.map(mapper-> mapper.getTimeWorkMinutes())
				.reduce((a,b)->a + b)
				.orElse(0);
	}

}
