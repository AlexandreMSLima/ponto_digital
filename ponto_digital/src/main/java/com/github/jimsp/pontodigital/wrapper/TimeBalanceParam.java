package com.github.jimsp.pontodigital.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder=true)
public class TimeBalanceParam {
	
	private final Integer workDayMinutes;
	private final Integer intervalMinutes;

}
