package com.github.jimsp.pontodigital.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.report.BalanceWorkDay;
import com.github.jimsp.pontodigital.wrapper.BalanceWorkParam;
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public final class CalculateWorkDays implements BiFunction<BalanceWorkParam, Predicate<Date>, BalanceWorkDay> {

	public static CalculateWorkDays create(final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay, //
			final BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay, //
			final BiFunction<Employer, TimeBalanceParam, Integer> timeBalance) {
		return new CalculateWorkDays(timeWorkedDuringTheDay, timeIntervalDuringTheDay, timeBalance);
	}

	private final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay;
	private final BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay;
	private final BiFunction<Employer, TimeBalanceParam, Integer> timeBalance;

	private CalculateWorkDays(final BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay, //
			final BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay, //
			final BiFunction<Employer, TimeBalanceParam, Integer> timeBalance) {
		this.timeWorkedDuringTheDay = timeWorkedDuringTheDay;
		this.timeIntervalDuringTheDay = timeIntervalDuringTheDay;
		this.timeBalance = timeBalance;
	}

	@Override
	public BalanceWorkDay apply(final BalanceWorkParam balanceWorkParam, final Predicate<Date> itsTheSameDay) {
		final Integer dayValue = Integer.valueOf(new SimpleDateFormat("dd").format(balanceWorkParam.getDay()));
		final Integer workDayMinutes = timeWorkedDuringTheDay.apply(balanceWorkParam.getEmployer(), itsTheSameDay);
		final Integer intervalMinutes = timeIntervalDuringTheDay.apply(balanceWorkParam.getEmployer(), itsTheSameDay);
		final Integer timeBalanceMinutes = timeBalance.apply(balanceWorkParam.getEmployer(), //
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
