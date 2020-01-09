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
    private String phone;
    private String otp;
    private String active;
    private String passport;
    private String drivingLicense;
    
    
	public UserLoginInformation() {
		super();
	}
    
	public UserLoginInformation(String username, String password, String email, String phone, String active, String passport, String drivingLicense) {
		super();
		this.username = username;
		this.password = password.toCharArray();
		this.email = email;
		this.phone = phone;
		this.active = active;
		this.passport = passport;
		this.drivingLicense = drivingLicense;
	}
}
