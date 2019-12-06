package com.banking.opb.service.impl;

import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.service.IOTPService;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
public class OTPServiceImpl implements IOTPService {
//cache based on username and OPT MAX 8 
	private static final Integer EXPIRE_MINS = 5;
	private LoadingCache<String, Integer> otpCache;

	public OTPServiceImpl() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

//This method is used to push the opt number against Key. Rewrite the OTP if it exists
	// Using user id as key
	@Override
	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	// This method is used to return the OPT number against Key->Key values is
	// username
	@Override
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}
	//This method is used to verify the OTP
	@Override
	public String verifyOTP(UserLoginInformation userinfo) {
		String response = "Invalid OTP";
		if(getOtp(userinfo.getEmail()) == Integer.valueOf(userinfo.getOtp())) {
			response = "Success";
			clearOTP(userinfo.getEmail());
		}
		return response;
	}
//This method is used to clear the OTP catched already
	@Override
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
}