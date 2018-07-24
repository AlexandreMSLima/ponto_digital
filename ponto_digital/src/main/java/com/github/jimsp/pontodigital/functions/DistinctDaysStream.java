package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Date;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;

public final class DistinctDaysStream {
	public static Stream<Date> of(final Employer employer) {
		
		return $ //
				.of(employer) //
				.map(periud -> $.formartDate().apply(periud.getEntry())) //
				.sorted()
				.distinct() //
				.map($.parseDate());
	}
}
