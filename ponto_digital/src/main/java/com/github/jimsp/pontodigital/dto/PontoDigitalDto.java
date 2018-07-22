package com.github.jimsp.pontodigital.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class PontoDigitalDto {

	@NotBlank
	private final String today;
	
	@NotBlank
	private final String periodStart;
	
	@NotEmpty
	@Valid
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
