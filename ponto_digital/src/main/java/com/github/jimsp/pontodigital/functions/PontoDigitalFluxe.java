package com.github.jimsp.pontodigital.functions;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.report.BalanceWorkDay;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;
import com.github.jimsp.pontodigital.wrapper.BalanceWorkParam;

public final class PontoDigitalFluxe implements Function<PontoDigitalDto, List<PontoDigitalReport>>{

	public static PontoDigitalFluxe create(final BiFunction<BalanceWorkParam, Predicate<Date>, BalanceWorkDay> calculateWorkDays) {
		return new PontoDigitalFluxe(calculateWorkDays);
	}

	private final BiFunction<BalanceWorkParam, Predicate<Date>, BalanceWorkDay> calculateWorkDays;

	private PontoDigitalFluxe(final BiFunction<BalanceWorkParam, Predicate<Date>, BalanceWorkDay> calculateWorkDays) {
		this.calculateWorkDays = calculateWorkDays;
	}

	@Override
	public List<PontoDigitalReport> apply(final PontoDigitalDto pontoDigitalDto) {

		final List<PontoDigitalReport> pontoDigitalReports = new ArrayList<>();
		$ //
				.of(pontoDigitalDto) //
				.forEach(employer -> { //

					final List<BalanceWorkDay> balanceWorkDays = //
							DistinctDaysStream.of(employer) //
									.map(day -> calculateWorkDays //
											.apply(BalanceWorkParam //
													.builder() //
													.employer(employer) //
													.day(day) //
													.build(), $.itsSameDay(day))) //
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
