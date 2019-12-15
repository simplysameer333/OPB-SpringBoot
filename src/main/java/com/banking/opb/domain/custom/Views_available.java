package com.banking.opb.domain.custom;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Views_available
{
	private String id;
	@JsonProperty("short_name")
    private String short_name;
	@JsonProperty("description")
    private String description;
	@JsonProperty("is_public")
    private boolean is_public;
	@JsonProperty("alias")
    private String alias;
	@JsonProperty("hide_metadata_if_alias_used")
    private boolean hide_metadata_if_alias_used;
	@JsonProperty("can_add_comment")
    private boolean can_add_comment;
	@JsonProperty("can_add_corporate_location")
    private boolean can_add_corporate_location;
	@JsonProperty("can_add_image")
    private boolean can_add_image;
	@JsonProperty("can_add_image_url")
    private boolean can_add_image_url;
	@JsonProperty("can_add_more_info")
    private boolean can_add_more_info;
	@JsonProperty("can_add_open_corporates_url")
    private boolean can_add_open_corporates_url;
	@JsonProperty("can_add_physical_location")
    private boolean can_add_physical_location;
	@JsonProperty("can_add_private_alias")
    private boolean can_add_private_alias;
	@JsonProperty("can_add_public_alias")
    private boolean can_add_public_alias;
	@JsonProperty("can_add_tag")
    private boolean can_add_tag;
	@JsonProperty("can_add_url")
    private boolean can_add_url;
	@JsonProperty("can_add_where_tag")
    private boolean can_add_where_tag;
	@JsonProperty("can_delete_comment")
    private boolean can_delete_comment;
	@JsonProperty("can_delete_corporate_location")
    private boolean can_delete_corporate_location;
	@JsonProperty("can_delete_image")
    private boolean can_delete_image;
	@JsonProperty("can_delete_physical_location")
    private boolean can_delete_physical_location;
	@JsonProperty("can_delete_tag")
    private boolean can_delete_tag;
	@JsonProperty("can_delete_where_tag")
    private boolean can_delete_where_tag;
	@JsonProperty("can_edit_owner_comment")
    private boolean can_edit_owner_comment;
	@JsonProperty("can_see_bank_account_balance")
    private boolean can_see_bank_account_balance;
	@JsonProperty("can_see_bank_account_bank_name")
    private boolean can_see_bank_account_bank_name;
	@JsonProperty("can_see_bank_account_currency")
    private boolean can_see_bank_account_currency;
	@JsonProperty("can_see_bank_account_iban")
    private boolean can_see_bank_account_iban;
	@JsonProperty("can_see_bank_account_label")
    private boolean can_see_bank_account_label;
	@JsonProperty("can_see_bank_account_national_identifier")
    private boolean can_see_bank_account_national_identifier;
	@JsonProperty("can_see_bank_account_number")
    private boolean can_see_bank_account_number;
	@JsonProperty("can_see_bank_account_owners")
    private boolean can_see_bank_account_owners;
	@JsonProperty("can_see_bank_account_swift_bic")
    private boolean can_see_bank_account_swift_bic;
	@JsonProperty("can_see_bank_account_type")
    private boolean can_see_bank_account_type;
	@JsonProperty("can_see_comments")
    private boolean can_see_comments;
	@JsonProperty("can_see_corporate_location")
    private boolean can_see_corporate_location;
	@JsonProperty("can_see_image_url")
    private boolean can_see_image_url;
	@JsonProperty("can_see_images")
    private boolean can_see_images;
	@JsonProperty("can_see_more_info")
    private boolean can_see_more_info;
	@JsonProperty("can_see_open_corporates_url")
    private boolean can_see_open_corporates_url;
	@JsonProperty("can_see_other_account_bank_name")
    private boolean can_see_other_account_bank_name;
	@JsonProperty("can_see_other_account_iban")
    private boolean can_see_other_account_iban;
	@JsonProperty("can_see_other_account_kind")
    private boolean can_see_other_account_kind;
	@JsonProperty("can_see_other_account_metadata")
    private boolean can_see_other_account_metadata;
	@JsonProperty("can_see_other_account_national_identifier")
    private boolean can_see_other_account_national_identifier;
	@JsonProperty("can_see_other_account_number")
    private boolean can_see_other_account_number;
	@JsonProperty("can_see_other_account_swift_bic")
    private boolean can_see_other_account_swift_bic;
	@JsonProperty("can_see_owner_comment")
    private boolean can_see_owner_comment;
	@JsonProperty("can_see_physical_location")
    private boolean can_see_physical_location;
	@JsonProperty("can_see_private_alias")
    private boolean can_see_private_alias;
	@JsonProperty("can_see_public_alias")
    private boolean can_see_public_alias;
	@JsonProperty("can_see_tags")
    private boolean can_see_tags;
	@JsonProperty("can_see_transaction_amount")
    private boolean can_see_transaction_amount;
	@JsonProperty("can_see_transaction_balance")
    private boolean can_see_transaction_balance;
	@JsonProperty("can_see_transaction_currency")
    private boolean can_see_transaction_currency;
	@JsonProperty("can_see_transaction_description")
    private boolean can_see_transaction_description;
	@JsonProperty("can_see_transaction_finish_date")
    private boolean can_see_transaction_finish_date;
	@JsonProperty("can_see_transaction_metadata")
    private boolean can_see_transaction_metadata;
	@JsonProperty("can_see_transaction_other_bank_account")
    private boolean can_see_transaction_other_bank_account;
	@JsonProperty("can_see_transaction_start_date")
    private boolean can_see_transaction_start_date;
	@JsonProperty("can_see_transaction_this_bank_account")
    private boolean can_see_transaction_this_bank_account;
	@JsonProperty("can_see_transaction_type")
    private boolean can_see_transaction_type;
	@JsonProperty("can_see_url")
    private boolean can_see_url;
	@JsonProperty("can_see_where_tag")
    private boolean can_see_where_tag;
}
