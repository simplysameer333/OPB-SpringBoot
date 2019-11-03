package com.banking.opb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Address {
    @JsonProperty("line_1")
    private String street;
    private String city;
    private String postcode;
    private String country;
}
