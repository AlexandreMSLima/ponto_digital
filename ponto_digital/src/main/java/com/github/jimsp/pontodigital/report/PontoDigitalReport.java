package com.github.jimsp.pontodigital.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder(toBuilder=true)
@ToString
public class PontoDigitalReport {
	
	private String pisNumber;
	private String name;
	private Integer balanceTime;
	private final List<BalanceWorkDay> balanceWorkDays;
}
