package com.github.jimsp.pontodigital.wrapper;

import java.util.Date;

import com.github.jimsp.pontodigital.dto.Employer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BalanceWorkParam {
	
	private final Employer employer;
	private final Date day;

}
