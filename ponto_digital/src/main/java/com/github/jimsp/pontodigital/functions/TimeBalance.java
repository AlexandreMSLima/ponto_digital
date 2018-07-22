package com.github.jimsp.pontodigital.functions;

import com.github.jimsp.pontodigital.dto.Employer;

public final class TimeBalance {
	
	public static TimeBalance create() {
		return new TimeBalance();
	}
	
	private TimeBalance() {
		
	}

	public Integer apply(final Employer employer, final Integer workDayMinutes, final Integer intervalMinutes) {
		final Integer minimumRestIntervalInMinutes = employer.getWorkload().getMinimumRestIntervalInMinutes();
		final Integer workloadInMinutes = employer.getWorkload().getWorkloadInMinutes();

		final Integer balanceDay = workloadInMinutes - workDayMinutes;
		final Integer balanceInterval = minimumRestIntervalInMinutes - intervalMinutes;

		return balanceInterval - balanceDay;
	}
}
