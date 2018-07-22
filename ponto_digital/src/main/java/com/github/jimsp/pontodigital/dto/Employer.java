package com.github.jimsp.pontodigital.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class Employer {

	@NotNull
	@Valid
	private final Workload workload;
	
	@NotBlank
	private final String pisNumber;
	
	@NotBlank
	private final String name;
	
	@NotEmpty
	private final List<String> entries;

	@JsonCreator
	public Employer( //
			@JsonProperty("workload") final Workload workload, //
			@JsonProperty("pis_number") final String pisNumber, //
			@JsonProperty("name") final String name, //
			@JsonProperty("entries") final List<String> entries) {
		this.workload = workload;
		this.pisNumber = pisNumber;
		this.name = name;
		this.entries = entries;
	}

}
