package com.github.jimsp.pontodigital.functions;
import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeWorkedDuringTheDay implements BiFunction<Employer, Predicate<Date>, Integer> {

	public static TimeWorkedDuringTheDay create() {
		return new TimeWorkedDuringTheDay();
	}

	private TimeWorkedDuringTheDay() {

	}

	@Override
	public Integer apply(final Employer employer, final Predicate<Date> itsTheSameDay) {
		return $ //
				.of(employer) //
				.filter(periud->itsTheSameDay.test(periud.getEntry())) //
				.map(mapper-> mapper.getTimeWorkMinutes())
				.reduce((a,b)->a + b)
				.orElse(0);
	}
}
