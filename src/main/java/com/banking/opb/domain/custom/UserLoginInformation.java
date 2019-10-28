package com.banking.opb.domain.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoginInformation {
    @JsonProperty("user_id")
    private String userId;
    private String username;
    private char[] password;
    private String email;
    private String authToken;
}