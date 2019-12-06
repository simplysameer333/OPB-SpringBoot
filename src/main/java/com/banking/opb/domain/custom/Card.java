package com.banking.opb.domain.custom;

import com.banking.opb.domain.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Card {
    //@JsonProperty("id")
    private String id;
    @JsonProperty("bank_id")
    private String bank_id;
    //@JsonProperty("bank_card_number")
    private String cardnumber;
    //@JsonProperty("name_on_card")
    private String nameoncard;
    //@JsonProperty("expires_date")
    private String expirydate;
    private String cvv;
    private String branchId;
    private String accountId;
    private String phone;
    private boolean isDefault;
    private int lastCode;
    //@JsonProperty("account")
    private Account account;
    public Card() {
    	super();
    }
    public Card(String cardNumber, String nameOnCard, String expiryDate, String cvv) {
    	this.cardnumber=cardNumber;
    	this.nameoncard=nameOnCard;
    	this.expirydate=expiryDate;
    	this.cvv=cvv;
    }
}
