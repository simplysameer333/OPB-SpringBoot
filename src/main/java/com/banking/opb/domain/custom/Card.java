package com.banking.opb.domain.custom;

import java.util.List;

import com.banking.opb.domain.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Card
{
	@JsonProperty("bank_id")
	private String bank_id;
	@JsonProperty("bank_card_number")
	private String cardnumber;
	@JsonProperty("name_on_card")
	private String nameoncard;
	/*@JsonProperty("issue_number")
	private String issueNumber;
	@JsonProperty("serial_number")
	private String serialNumber;*/
	@JsonProperty("technology")
	private String technology;
	/*@JsonProperty("enabled")
	private boolean enabled;
	@JsonProperty("cancelled")
	private boolean cancelled;
	@JsonProperty("on_hot_list")
	private boolean onHotList;
	@JsonProperty("valid_from_date")
    private String valid_from_date;*/
	@JsonProperty("expires_date")
    private String expirydate;
	/*@JsonProperty("networks")
    private List<String> networks;
	@JsonProperty("allows")
    private List<String> allows;*/
	@JsonProperty("account")
    private Account account;
	/*@JsonProperty("replacement")
    private Replacement replacement;
	@JsonProperty("pin_reset")
    private List<Pin_reset> pin_reset;
    @JsonProperty("collected")
    private String collected;
    @JsonProperty("posted")
    private String posted;*/

	private String cvv;
	private String branchId;
	private String accountId;
	private String phone;
	private boolean isDefault;
	private int lastCode;
	
}