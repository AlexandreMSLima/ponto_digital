package com.github.jimsp.pontodigital.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.wrapper.PeriudWorkParam;

public final class PontoDigitalDtoStream {

	public static Stream<Employer> of(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalDto //
				.getEmployees() //
				.stream();
	}

	public static Stream<PeriudWorkParam> of(final Employer employer) {
		final List<Date> entries = employer //
				.getEntries() //
				.stream() //
				.sorted() //
				.map(DateFormat.parseDateTime()) //
				.collect(Collectors.toList());

		final List<PeriudWorkParam> periudWorkParams = new ArrayList<>();
		int i = 0;
		while (i < entries.size()) {
			final Date entry = entries.get(i);
			final Date exit = entries.get(i + 1);

			if (sameDay(entry, exit)) {
				periudWorkParams //
				.add( //
						PeriudWorkParam //
								.builder() //
								.entry(entry) //
								.exit(exit) //
								.build());
			}else {
				periudWorkParams //
				.add( //
						PeriudWorkParam //
								.builder() //
								.entry(entry) //
								.exit(DateFormat.parseTimeLastHour().apply(entry)) //
								.build());
				periudWorkParams //
				.add( //
						PeriudWorkParam //
								.builder() //
								.entry(DateFormat.parseTimeZeroHour().apply(exit)) //
								.exit(exit) //
								.build());
			}

			
			i = i + 2;
		}

		return periudWorkParams //
				.stream();
	}

	private static boolean sameDay(final Date entry, final Date exit) {
		return DateFormat //
				.formartDate() //
				.apply(entry) //
				.equals( //
						DateFormat //
								.formartDate() //
								.apply(exit));
	}
}
