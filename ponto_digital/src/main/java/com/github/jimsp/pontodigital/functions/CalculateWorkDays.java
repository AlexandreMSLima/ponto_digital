package com.github.jimsp.pontodigital.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;

import com.github.jimsp.pontodigital.BalanceWorkDay;
import com.github.jimsp.pontodigital.dto.Employer;

public final class CalculateWorkDays implements BiFunction<Employer, Date, BalanceWorkDay>{
	
	public static CalculateWorkDays create() {
		return new CalculateWorkDays();
	}
	
	private final TimeWorkedDuringTheDay timeWorkedDuringTheDay = TimeWorkedDuringTheDay.create();
	private final TimeIntervalDuringTheDay timeIntervalDuringTheDay = TimeIntervalDuringTheDay.create();
	private final TimeBalance timeBalance = TimeBalance.create();
	
	private CalculateWorkDays() {
		
	}
	
	@Override
	public BalanceWorkDay apply(final Employer employer, final Date day) {
		final Integer dayValue = Integer.valueOf(new SimpleDateFormat("dd").format(day));
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(day);
		final Integer workDayMinutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		final Integer intervalMinutes = timeIntervalDuringTheDay.apply(employer, itsTheSameDay);
		final Integer timeBalanceMinutes = timeBalance.apply(employer, workDayMinutes, intervalMinutes);

		return BalanceWorkDay //
				.builder() //
				.day(dayValue) //
				.balanceDayMinutes(timeBalanceMinutes) //
				.workDayMinutes(workDayMinutes) //
				.build();
	}

}
