package com.banking.opb.domain;

import java.util.List;

import org.joda.money.Money;

import com.banking.opb.domain.custom.Views_available;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
public class Account {
    private String id;

    @JsonProperty("user_id")
    private String userId = "";
    
    @JsonProperty("label")
    private String label;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("branch_id")
    private String branchId;

    @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
    @JsonSerialize(using = MoneyJson.MoneySerializer.class)
    private Money balance;

    private String type;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("swist_bic")
    private String bic;

    @JsonProperty("account_routing")
    private AccountRouting accountRouting;
    
    @JsonProperty("views_available")
    private List<Views_available> views_available;

}
