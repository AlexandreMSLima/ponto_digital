package com.github.jimsp.pontodigital.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class Workload {

	private final Integer workloadInMinutes;
	private final Integer minimumRestIntervalInMinutes;
	private final List<Day> days;

	@JsonCreator
	public Workload( //
			@JsonProperty("workload_in_minutes") final Integer workloadInMinutes, //
			@JsonProperty("minimum_rest_interval_in_minutes") final Integer minimumRestIntervalInMinutes,
			@JsonProperty("days") final List<Day> days) {
		this.workloadInMinutes = workloadInMinutes;
		this.minimumRestIntervalInMinutes = minimumRestIntervalInMinutes;
		this.days = days;
	}

}
