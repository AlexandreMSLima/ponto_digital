package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;

public final class DistinctDaysStream {
	public static Stream<Date> of(final Employer employer) {
		
		return PontoDigitalDtoStream //
				.of(employer) //
				.map(periud -> DateFormat.formartDate().apply(periud.getEntry())) //
				.sorted()
				.distinct() //
				.map(DateFormat.parseDate());
	}
}
