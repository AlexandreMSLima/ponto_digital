package com.github.jimsp.pontodigital.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.FunctionalCatalog;
import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.report.BalanceWorkDay;
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public final class CalculateWorkDays implements BiFunction<Employer, Date, BalanceWorkDay> {

	public static CalculateWorkDays create() {
		return new CalculateWorkDays();
	}

	private final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay = FunctionalCatalog.timeWorkedDuringTheDay();
	private final BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay = FunctionalCatalog.timeIntervalDuringTheDay();
	private final BiFunction<Employer, TimeBalanceParam, Integer> timeBalance = FunctionalCatalog.timeBalance();

	private CalculateWorkDays() {
		
	}

	@Override
	public BalanceWorkDay apply(final Employer employer, final Date day) {
		final Integer dayValue = Integer.valueOf(new SimpleDateFormat("dd").format(day));
		final Predicate<Date> itsTheSameDay = FunctionalCatalog.itsSameDay(day);
		final Integer workDayMinutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		final Integer intervalMinutes = timeIntervalDuringTheDay.apply(employer, itsTheSameDay);
		final Integer timeBalanceMinutes = timeBalance.apply(employer, //
				TimeBalanceParam //
						.builder() //
						.workDayMinutes(workDayMinutes) //
						.intervalMinutes(intervalMinutes) //
						.build());

		return BalanceWorkDay //
				.builder() //
				.day(dayValue) //
				.balanceDayMinutes(timeBalanceMinutes) //
				.workDayMinutes(workDayMinutes) //
				.build();
	}

}
