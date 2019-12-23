package com.banking.opb.domain;

import org.joda.money.Money;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class TransactionRequest {

	@JsonProperty("to")
    private DestAccount to;

    @JsonProperty("value")
    /*@JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
    @JsonSerialize(using = MoneyJson.MoneySerializer.class)*/
    private Amount value;
	
    @JsonProperty("description")
    private String description;

    @Data
    @NoArgsConstructor @AllArgsConstructor
    public static class DestAccount {
        @JsonProperty("bank_id")
        private String bankId;

        @JsonProperty("account_id")
        private String accountId;
    }

}
