package com.github.jimsp.pontodigital.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;

public final class DistinctDaysStream {
	public static Stream<Date> of(final Employer employer) {
		return PontoDigitalDtoStream //
				.of(employer) //
				.map(periud -> new SimpleDateFormat("yyyyMMdd") //
						.format(periud.getEntry())) //
				.sorted()
				.distinct() //
				.map(periud -> {
					try {
						return new SimpleDateFormat("yyyyMMdd").parse(periud);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				});
	}
}
