package com.banking.opb.domain.custom;

import com.banking.opb.Application;
import com.banking.opb.domain.MoneyJson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

@Data
public class Customer {
    @JsonProperty("legal_name")
    private String legalName;

    @JsonProperty("mobile_phone_number")
    private String mobilePhoneNumber;

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("customer_number")
    private String customerNumber;
    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Application.ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
    private Date dob;

    @JsonProperty("relationship_status")
    private String relationshipStatus;
    @JsonProperty("highest_education_attained")
    private String highestEducationAttained;

    @JsonProperty("employment_status")
    private String employmentStatus;
    @JsonProperty("kyc_status")
    private boolean kycStatus;
    private String title;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("branch_id")
    private String branchId;
    private String nameSuffix;

    @JsonProperty("credit_rating")
    private CreditRating creditRating;

    @JsonProperty("credit_limit")
    @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
    @JsonSerialize(using = MoneyJson.MoneySerializer.class)
    private Money creditLimit;
}

@Data
class CreditRating {
    private String rating;
    private String source;
}


