package com.github.jimsp.pontodigital.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class PontoDigitalDto {

	private final String today;
	private final String periodStart;
	private final List<Employer> employees;

	public PontoDigitalDto( //
			@JsonProperty("today") final String today, //
			@JsonProperty("period_start") final String periodStart, //
			@JsonProperty("employees") final List<Employer> employees) {
		super();
		this.today = today;
		this.periodStart = periodStart;
		this.employees = employees;
	}
}
