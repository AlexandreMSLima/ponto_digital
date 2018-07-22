package com.github.jimsp.pontodigital;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.functions.CalculateWorkDays;
import com.github.jimsp.pontodigital.functions.DistinctDaysStream;
import com.github.jimsp.pontodigital.functions.PontoDigitalDtoStream;

public final class PontoDigitalFluxe {

	public static PontoDigitalFluxe create() {
		return new PontoDigitalFluxe();
	}

	private final CalculateWorkDays calculateWorkDays = CalculateWorkDays.create();

	private PontoDigitalFluxe() {

	}

	public List<PontoDigitalReport> processDto(final PontoDigitalDto pontoDigitalDto) {

		final List<PontoDigitalReport> pontoDigitalReports = new ArrayList<>();
		PontoDigitalDtoStream //
				.of(pontoDigitalDto) //
				.forEach(employer -> { //

					final List<BalanceWorkDay> balanceWorkDays = //
							DistinctDaysStream.of(employer) //
									.map(day -> calculateWorkDays.apply(employer, day)) //
									.collect(Collectors.toList());

					final Integer balanceTime = balanceWorkDays //
							.stream() //
							.map(mapper -> mapper.getBalanceDayMinutes()) //
							.reduce((a, b) -> a + b) //
							.get();

					pontoDigitalReports //
							.add(PontoDigitalReport //
									.builder() //
									.pisNumber(employer.getPisNumber()) //
									.name(employer.getName()) //
									.balanceTime(balanceTime) //
									.balanceWorkDays(balanceWorkDays) //
									.build());
				});

		return pontoDigitalReports;
	}
}
