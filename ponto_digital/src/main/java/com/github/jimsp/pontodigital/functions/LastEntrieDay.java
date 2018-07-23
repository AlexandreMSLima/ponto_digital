package com.github.jimsp.pontodigital.functions;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class LastEntrieDay implements BiFunction<List<String>, Predicate<Date>, Date>{
	
	public static LastEntrieDay create() {
		return new LastEntrieDay();
	}
	
	private LastEntrieDay() {
		
	}
	
	public Date apply(final List<String> entries, final Predicate<Date> itsTheSameDay) {
		return entries //
				.stream() //
				.map(DateFormat.parseDateTime()) //
				.filter(itsTheSameDay) //
				.reduce((a, b) -> a.before(b) ? b : a) //
				.get();
	}

}
