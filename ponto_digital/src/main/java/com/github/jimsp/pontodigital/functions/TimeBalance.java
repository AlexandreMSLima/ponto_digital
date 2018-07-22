package com.github.jimsp.pontodigital.functions;

import java.util.function.BiFunction;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public final class TimeBalance implements BiFunction<Employer, TimeBalanceParam, Integer>{
	
	public static TimeBalance create() {
		return new TimeBalance();
	}
	
	private TimeBalance() {
		
	}

	public Integer apply(final Employer employer, final TimeBalanceParam timeBalanceParam) {
		final Integer minimumRestIntervalInMinutes = employer.getWorkload().getMinimumRestIntervalInMinutes();
		final Integer workloadInMinutes = employer.getWorkload().getWorkloadInMinutes();

		final Integer balanceDay = workloadInMinutes - timeBalanceParam.getWorkDayMinutes();
		final Integer balanceInterval = minimumRestIntervalInMinutes - timeBalanceParam.getIntervalMinutes();

		return balanceInterval - balanceDay;
	}
}
