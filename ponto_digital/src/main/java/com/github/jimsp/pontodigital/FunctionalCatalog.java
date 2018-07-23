package com.github.jimsp.pontodigital;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.functions.CalculateWorkDays;
import com.github.jimsp.pontodigital.functions.DateToMilliseconds;
import com.github.jimsp.pontodigital.functions.FirstEntrieDay;
import com.github.jimsp.pontodigital.functions.Interval;
import com.github.jimsp.pontodigital.functions.ItsTheSameDay;
import com.github.jimsp.pontodigital.functions.LastEntrieDay;
import com.github.jimsp.pontodigital.functions.MillisecondsConversion;
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
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public interface FunctionalCatalog {
	
	public static FunctionalCatalog $() {
		return new FunctionalCatalog() {
		};
	}

	public default BiFunction<Employer, Date, BalanceWorkDay> calculateWorkDays(){
		return CalculateWorkDays.create();
	}
	
	public default Function<Date, Long> dateToMilliseconds(){
		return DateToMilliseconds.create();
	}
	
	public default BiFunction<List<String>, Predicate<Date>, Date> firstEntrieDay(){
		return FirstEntrieDay.create();
	}
	
	public default BinaryOperator<Long> interval(){
		return Interval.create();
	}
	
	public default Predicate<Date> itsSameDay(final Date day){
		return ItsTheSameDay.create(day);
	}
	
	public default BiFunction<List<String>, Predicate<Date>, Date> lastEntries(){
		return LastEntrieDay.create();
	}
	
	public default Function<Long, Integer> millisecondsToSecondsConversion(){
		return MillisecondsConversion.createToSeconds();
	}
	
	public default Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe(){
		return PontoDigitalFluxe.create();
	}
	
	public default Function<InputStream, PontoDigitalDto> pontoDigitalRead(){
		return PontoDigitalRead.create();
	}
	
	public default Consumer<PontoDigitalDto> pontoDigitalValidate(){
		return PontoDigitalValidate.create();
	}
	
	public default BiConsumer<OutputStream, List<PontoDigitalReport>> pontoDigitalWrite(){
		return PontoDigitalWrite.create();
	}
	
	public default BiFunction<Long, Long, Long> subtraction(){
		return Subtraction.create();
	}
	
	public default BiFunction<Employer, TimeBalanceParam, Integer> timeBalance(){
		return TimeBalance.create();
	}
	
	public default BiFunction<Employer, Predicate<Date>, Integer> timeIntervalDuringTheDay(){
		return TimeIntervalDuringTheDay.create();
	}
	
	public default Function<Employer, Integer> timeIntervalDuringThePeriod(){
		return TimeIntervalDuringThePeriod.create();
	}
	
	public default BiFunction<Employer, Predicate<Date>, Integer> timeWorkedDuringTheDay(){
		return TimeWorkedDuringTheDay.create();
	}
	
	public default Function<Employer, Integer> timeWorkedDuringThePeriod(){
		return TimeWorkedDuringThePeriod.create();
	}
}
