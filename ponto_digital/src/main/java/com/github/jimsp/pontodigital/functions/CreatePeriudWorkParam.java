package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import com.github.jimsp.pontodigital.wrapper.PeriudWorkParam;

public final class CreatePeriudWorkParam implements BiFunction<Date, Date, List<PeriudWorkParam>>{
	
	public static CreatePeriudWorkParam create() {
		return new CreatePeriudWorkParam();
	}
	
	private CreatePeriudWorkParam() {
		
	}
	
	@Override
	public List<PeriudWorkParam> apply(final Date entry, final Date exit) {
		final List<PeriudWorkParam> periudWorkParams = new ArrayList<>();
		
		if ($.itsSameDay(entry).test(exit)) {

			final Integer timeWorkMinutes = $.millisecondsToSecondsConversion()
					.apply($.interval().apply(entry.getTime(), exit.getTime()));

			periudWorkParams.add(PeriudWorkParam //
					.builder() //
					.entry(entry) //
					.exit(exit) //
					.timeWorkMinutes(timeWorkMinutes) //
					.build());
		} else {
			final Integer timeWorkMinutesAtLast = $.millisecondsToSecondsConversion() //
					.apply($.interval() //
							.apply(entry.getTime(), //
									$.parseTimeLastHour() //
											.apply(entry) //
											.getTime()));

			final Integer timeWorkMinutesOfZeroHour = $.millisecondsToSecondsConversion() //
					.apply($.interval() //
							.apply($.parseTimeZeroHour() //
									.apply(exit) //
									.getTime(), //
									exit.getTime()));

			periudWorkParams //
					.add( //
							PeriudWorkParam //
									.builder() //
									.entry(entry) //
									.exit($.parseTimeLastHour().apply(entry)) //
									.timeWorkMinutes(timeWorkMinutesAtLast) //
									.build());
			periudWorkParams //
					.add( //
							PeriudWorkParam //
									.builder() //
									.entry($.parseTimeZeroHour().apply(exit)) //
									.exit(exit) //
									.timeWorkMinutes(timeWorkMinutesOfZeroHour) //
									.build());
		}
		
		return periudWorkParams;
	}

}
