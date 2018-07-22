package com.github.jimsp.pontodigital.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public final class Workload {

	@Min(0)
	@NotNull
	private final Integer workloadInMinutes;
	
	@Min(0)
	@NotNull
	private final Integer minimumRestIntervalInMinutes;
	
	@NotEmpty
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
