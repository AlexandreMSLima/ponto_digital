package com.github.jimsp.pontodigital.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder=true)
public class BalanceWorkDay {

	private final Integer day;
	private final Integer workDayMinutes;
	private final Integer balanceDayMinutes;
}
