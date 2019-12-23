package com.banking.opb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Amount {
	
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("amount")
	private String amount;

}
