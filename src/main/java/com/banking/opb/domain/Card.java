package com.banking.opb.domain;

import lombok.Data;

@Data
public class Card {
    private String id;
    private String cardNumber;
    private String branchId;
    private String accountId;
    private String phone;
    private boolean isDefault;
    private int lastCode;
}
