package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;

public final class PontoDigitalDtoStream {

	public static Stream<Employer> of(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalDto //
				.getEmployees() //
				.stream();
	}

	public static Stream<Date> of(final Employer employer) {
		return employer //
				.getEntries() //
				.stream()
				.map(DateFormat.parseDate())
				.sorted();
	}
}
