package com.banking.opb.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.exception.ApiRequestException;
import com.banking.opb.service.ILoginService;
import com.banking.opb.service.IOTPService;
import com.banking.opb.utilities.BasicUtilities;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IOTPService otpService;

    @PostMapping(value = "/api/signUpUser", consumes = "application/json", produces = "application/json")
    public Map<String, String> signUp(@RequestBody UserLoginInformation userInfo) {
        String response = "Unable to create user";
        String username = loginService.singedUpUser(userInfo);
        if (username != null && !"MandatoryMissing".equals(username) && !"UserExists".equals(username))
            response = "Verify with OTP sent to phone";
        if ("UserExists".equals(username))
        	response = "User already exist!";
        return Collections.singletonMap("response", response);
    }

    @PostMapping(value = "/api/verifyUserOTP", consumes = "application/json", produces = "application/json")
    public Map<String, String> verifyUserOtp(@RequestBody UserLoginInformation userInfo) {
        String response = "Unable to verify user";
        String username = loginService.userOTPAuth(userInfo);
        if (username != null && !"Invalid OTP".equals(username))
            response = "Account verified successfully!";
        return Collections.singletonMap("response", response);
    }
    
    @PostMapping(value = "/api/loginUser", consumes = "application/json", produces = "application/json")    
    public Map<String, String> login(@RequestBody UserLoginInformation userInfo) {
        try {
            userInfo = loginService.login(userInfo);
            if (userInfo != null) {
                //log.info(userInfo.getUserId());
                BasicUtilities.session().setAttribute("activeuser", userInfo);
                Map<String, String> userInfoMap = new HashMap<String, String>();
                userInfoMap.put("email",userInfo.getEmail());
                userInfoMap.put("name",userInfo.getUsername());
                //userInfoMap.put("authtoken",userInfo.getAuthToken());
                if(userInfo.getDrivingLicense()!=null && userInfo.getPassport()!=null)
                	userInfoMap.put("kyc","done");
                else
                	userInfoMap.put("kyc","pending");
                return userInfoMap;
            }
        } catch (Exception e) {
            throw new ApiRequestException("error while getting user info", HttpStatus.NO_CONTENT, e);
        }
        return Collections.singletonMap("response", "Invalid credentials");
    }
    
    @PostMapping(value = "/api/kycUpdate", consumes = "application/json", produces = "application/json")    
    public Map<String, String> kycUser(@RequestBody UserLoginInformation userInfo) {
        try {
            String kycUpdate = loginService.kycUpdate(userInfo);
            if (kycUpdate != null) {
                //log.info(userInfo.getUserId());
                return Collections.singletonMap("response", kycUpdate);
            }
        } catch (Exception e) {
            throw new ApiRequestException("error while getting user info", HttpStatus.NO_CONTENT, e);
        }
        return Collections.singletonMap("response", "Unable to update profile");
    }
    
    @GetMapping(value = "/api/kycDetails/{email}", consumes = "application/json", produces = "application/json")    
    public UserLoginInformation kycDetails(@PathVariable("email") String email) {
    	
        return loginService.kycDetails(email);
    }
}