package com.github.jimsp.pontodigital;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.functions.CalculateWorkDays;
import com.github.jimsp.pontodigital.functions.CreatePeriudWorkParam;
import com.github.jimsp.pontodigital.functions.DateToMilliseconds;
import com.github.jimsp.pontodigital.functions.FirstEntrieDay;
import com.github.jimsp.pontodigital.functions.Interval;
import com.github.jimsp.pontodigital.functions.ItsTheSameDay;
import com.github.jimsp.pontodigital.functions.LastEntrieDay;
import com.github.jimsp.pontodigital.functions.MillisecondsConversion;
import com.github.jimsp.pontodigital.functions.PontoDigitalFileConsumer;
import com.github.jimsp.pontodigital.functions.PontoDigitalFluxe;
import com.github.jimsp.pontodigital.functions.PontoDigitalRead;
import com.github.jimsp.pontodigital.functions.PontoDigitalValidate;
import com.github.jimsp.pontodigital.functions.PontoDigitalWrite;
import com.github.jimsp.pontodigital.functions.Subtraction;
import com.github.jimsp.pontodigital.functions.TimeBalance;
import com.github.jimsp.pontodigital.functions.TimeIntervalDuringTheDay;
import com.github.jimsp.pontodigital.functions.TimeIntervalDuringThePeriod;
import com.github.jimsp.pontodigital.functions.TimeWorkedDuringTheDay;
import com.github.jimsp.pontodigital.functions.TimeWorkedDuringThePeriod;
import com.github.jimsp.pontodigital.report.BalanceWorkDay;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;
import com.github.jimsp.pontodigital.wrapper.BalanceWorkParam;
import com.github.jimsp.pontodigital.wrapper.PeriudWorkParam;
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public interface FunctionalCatalog {
	
	public static FunctionalCatalog $ = new FunctionalCatalog() {
	};
	
	public default Function<String, Date> parseDateTime() {
		return date -> {
			try {
				return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}
	
	public default Function<String, Date> parseDate() {
		return date -> {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}

	public default Function<Date, String> formartDateTime() {
		return date -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}
	
	public default Function<Date, String> formartDate() {
		return date -> new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public default Function<Date, Date> parseTimeZeroHour() {
		return date -> parseDateTime().apply(formartDate().apply(date).concat("T00:00:00"));
	}

	public default Function<Date, Date> parseTimeLastHour() {
		return date -> parseDateTime().apply(formartDate().apply(date).concat("T23:59:59"));
	}

	public default BiFunction<BalanceWorkParam, Predicate<Date>, BalanceWorkDay> calculateWorkDays() {
		return CalculateWorkDays.create(timeWorkedDuringTheDay(), timeIntervalDuringTheDay(), timeBalance());
	}

	public default Function<Date, Long> dateToMilliseconds() {
		return DateToMilliseconds.create();
	}

	public default BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay() {
		return FirstEntrieDay.create();
	}

	public default BinaryOperator<Long> interval() {
		return Interval.create();
	}

	public default Predicate<Date> itsSameDay(final Date day) {
		return ItsTheSameDay.create(day);
	}

	public default BiFunction<List<String>, Predicate<Date>, Date> lastEntries() {
		return LastEntrieDay.create();
	}

	public default Function<Long, Integer> millisecondsToSecondsConversion() {
		return MillisecondsConversion.createToSeconds();
	}

	public default Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe() {
		return PontoDigitalFluxe.create(calculateWorkDays());
	}

	public default Function<InputStream, PontoDigitalDto> pontoDigitalRead() {
		return PontoDigitalRead.create();
	}

	public default Consumer<PontoDigitalDto> pontoDigitalValidate() {
		return PontoDigitalValidate.create();
	}

	public default BiConsumer<OutputStream, List<PontoDigitalReport>> pontoDigitalWrite() {
		return PontoDigitalWrite.create();
	}

	public default BiFunction<Long, Long, Long> subtraction() {
		return Subtraction.create();
	}

	public default BiFunction<Employer, TimeBalanceParam, Integer> timeBalance() {
		return TimeBalance.create();
	}

	public default BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay() {
		return TimeIntervalDuringTheDay.create(interval(), millisecondsToSecondsConversion(), dateToMilliseconds(),
				timeWorkedDuringTheDay(), firstEntrieDay(), lastEntries());
	}

	public default Function<Employer, Integer> timeIntervalDuringThePeriod() {
		return TimeIntervalDuringThePeriod.create(interval(), millisecondsToSecondsConversion(), dateToMilliseconds(),
				timeWorkedDuringThePeriod());
	}

	public default BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay() {
		return TimeWorkedDuringTheDay.create();
	}

	public default Function<Employer, Integer> timeWorkedDuringThePeriod() {
		return TimeWorkedDuringThePeriod.create();
	}
	
	public default BiConsumer<InputStream, OutputStream> pontoDigitalFileConsumer() {
		return PontoDigitalFileConsumer.create(pontoDigitalFluxe());
	}

	public default Stream<Employer> of(final PontoDigitalDto pontoDigitalDto) {
		return pontoDigitalDto //
				.getEmployees() //
				.stream();
	}
	
	public default BiFunction<Date, Date, List<PeriudWorkParam>> createPeriudWorkParam(){
		return CreatePeriudWorkParam.create();
	}

	public default Stream<PeriudWorkParam> of(final Employer employer) {
		final List<Date> entries = employer //
				.getEntries() //
				.stream() //
				.sorted() //
				.map(parseDateTime()) //
				.collect(Collectors.toList());

		final List<PeriudWorkParam> periudWorkParams = new ArrayList<>();
		int i = 0;
		while (i < entries.size()) {
			final Date entry = entries.get(i);
			final Date exit = entries.get(i + 1);
			periudWorkParams.addAll(createPeriudWorkParam().apply(entry, exit));
			i = i + 2;
		}

		return periudWorkParams //
				.stream();
	}
}
