package com.github.jimsp.pontodigital.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class Employer {

	private final Workload workload;
	private final String pisNumber;
	private final String name;
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
