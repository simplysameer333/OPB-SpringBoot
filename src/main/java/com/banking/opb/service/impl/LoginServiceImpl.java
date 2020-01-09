package com.banking.opb.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import com.banking.opb.clientapi.DirectAuthenticationClient;
import com.banking.opb.domain.custom.SmsRequest;
import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.repo.IUserDao;
import com.banking.opb.service.ILoginService;
import com.banking.opb.service.IOTPService;
import com.banking.opb.service.ISmsSender;
import com.banking.opb.utilities.BasicUtilities;
import com.banking.opb.utilities.ConfigProperties;

@Service
public class LoginServiceImpl implements ILoginService {
    private static Map<String, UserLoginInformation> userCache = new HashMap<>();
    
    LoginServiceImpl() {
    	//userCache.put("simply_sameer",new UserLoginInformation("simply_sameer","test@123",""));
    }

    @Autowired
    private ConfigProperties properties;

    @Autowired
    private DirectAuthenticationClient directAuthenticationClient;
    
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOTPService otpService;
    @Autowired
    private ISmsSender twilioService;
    public String singedUpUser(UserLoginInformation userInfo) {
        if (BasicUtilities.isEmptyOrNullString(userInfo.getEmail()) || BasicUtilities.isEmptyOrNullString(userInfo.getUsername())
        		|| BasicUtilities.isEmptyOrNullString(userInfo.getPhone()) || BasicUtilities.isEmptyOrNullCharaterArray(userInfo.getPassword()))
            return "MandatoryMissing";
        String username = userDao.registerUser(userInfo);
        if (username == null)
        	return "Unable to create User !";
        if (username.equals("UserExists"))
        	return username;
        String otp = String.valueOf(otpService.generateOTP(userInfo.getEmail()));
        SmsRequest smsRequest = new SmsRequest(userInfo.getPhone(), otp);
        twilioService.sendSms(smsRequest);
        return username;
    }

    public UserLoginInformation login(UserLoginInformation userInfo) {  
    	  
        UserLoginInformation currentUser = userCache.get(userInfo.getEmail());
        /*if (currentUser != null
                && String.copyValueOf(currentUser.getPassword()).equals(String.copyValueOf(userInfo.getPassword()))) {*/
            /*String authToken = directAuthenticationClient.login(properties.getConfigValue("obp.username"),
                    properties.getConfigValue("obp.password"),
                    properties.getConfigValue("obp.consumerKey"));*/
        	/*String authToken = directAuthenticationClient.login("simply_sameer","Justme@123","alp1mr1btifdpwv32qekdm1mkjwqvjom45gyi4in");
            currentUser.setAuthToken(authToken);
            SecurityContextHolder.setContext(new SecurityContextImpl(
                    new UsernamePasswordAuthenticationToken(userInfo.getUsername(), authToken)));*/
           /* return currentUser;
        }*/
    	if (BasicUtilities.isEmptyOrNullString(userInfo.getEmail())
                || BasicUtilities.isEmptyOrNullCharaterArray(userInfo.getPassword()))
            return null;

    	UserLoginInformation username = userDao.login(userInfo);
        
        if (username != null && username.getActive().equals("N")) {
        	username.setUsername("Account not active");
            return username;
        } else if(username != null)
        	return username;
        return null;
    }

	@Override
	public String userOTPAuth(UserLoginInformation userInfo) {
        String username = otpService.verifyOTP(userInfo);
        if (username !=null && username.equals("Invalid OTP"))
        	return username;
       return  userDao.activateUser(userInfo);
	}
	
	@Override
    public String kycUpdate(UserLoginInformation userInfo) {
    	
		return userDao.kycUpdateUser(userInfo);
    }
	
	@Override
    public UserLoginInformation kycDetails(String email) {
		
		return userDao.kycUserDetails(email);
	}
}
