package com.banking.opb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Amount {
	
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("amount")
	private String amount;

}
