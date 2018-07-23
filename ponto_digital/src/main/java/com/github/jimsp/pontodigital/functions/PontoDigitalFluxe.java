package com.github.jimsp.pontodigital.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.jimsp.pontodigital.Context;
import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.report.BalanceWorkDay;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public final class PontoDigitalFluxe implements Function<PontoDigitalDto, List<PontoDigitalReport>>{

	public static PontoDigitalFluxe create() {
		return new PontoDigitalFluxe();
	}

	private final BiFunction<Employer, Date, BalanceWorkDay> calculateWorkDays = Context.calculateWorkDays();

	private PontoDigitalFluxe() {

	}

	@Override
	public List<PontoDigitalReport> apply(final PontoDigitalDto pontoDigitalDto) {

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
