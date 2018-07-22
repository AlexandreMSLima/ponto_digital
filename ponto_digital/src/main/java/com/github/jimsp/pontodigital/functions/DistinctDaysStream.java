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
				.map(date -> new SimpleDateFormat("yyyyMMdd") //
						.format(date)) //
				.sorted()
				.distinct() //
				.map(date -> {
					try {
						return new SimpleDateFormat("yyyyMMdd").parse(date);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				});
	}
}
