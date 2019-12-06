package com.banking.opb.service;

import com.banking.opb.domain.custom.UserLoginInformation;

public interface IOTPService {
	
	 int generateOTP(String key);
	 int getOtp(String key);
	 void clearOTP(String key);
	 String verifyOTP(UserLoginInformation userinfo);
}
