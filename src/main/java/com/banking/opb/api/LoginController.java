package com.banking.opb.api;

import com.banking.opb.domain.UserLoginInformation;
import com.banking.opb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginServiceImpl;
    private static Map<String, UserLoginInformation> userCache = new HashMap<>();

    @PostMapping(value = "/signUpUser")
    public String signUp(@RequestBody UserLoginInformation userInfo) {
        String username = loginServiceImpl.singedUpUser(userInfo);
        if (username != null)
            return "Success";
        return "Failed";
    }

    @GetMapping(value = "/loginUser")
    public UserLoginInformation login(@RequestBody UserLoginInformation userInfo) {
        return loginServiceImpl.login(userInfo);
    }
}