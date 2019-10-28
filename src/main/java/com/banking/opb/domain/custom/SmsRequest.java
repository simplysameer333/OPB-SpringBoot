package com.banking.opb.domain.custom;

import lombok.Getter;

@Getter
public class SmsRequest {
    private final String phoneNumber;
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
