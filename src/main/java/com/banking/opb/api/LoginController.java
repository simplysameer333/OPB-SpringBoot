package com.banking.opb.api;

import com.banking.opb.Utilities.ConfigProperties;
import com.banking.opb.clientapi.DirectAuthenticationClient;
import com.banking.opb.domain.UserLoginInformation;
import com.banking.opb.exception.ApiRequestException;
import com.banking.opb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginServiceImpl;

    @Autowired
    private DirectAuthenticationClient directAuthenticationClient;

    @Autowired
    private ConfigProperties properties;


    @PostMapping(value = "/api/signUpUser", consumes = "application/json", produces = "application/json")
    public Map<String, String> signUp(@RequestBody UserLoginInformation userInfo) {
        String response = "Failed";
        String username = loginServiceImpl.singedUpUser(userInfo);
        if (username != null && !"MandatoryMissing".equals(username) && !"UserExists".equals(username))
            response = "Success";
        return Collections.singletonMap("response", response);
    }

    @PostMapping(value = "/api/loginUser", consumes = "application/json", produces = "application/json")
    public Map<String, String> login(@RequestBody UserLoginInformation userInfo, HttpServletRequest request) {
        try {
            userInfo = loginServiceImpl.login(userInfo);
            String token = directAuthenticationClient.login("simply_sameer", "Justme@123",
                    properties.getConfigValue("obp.consumerKey"));

            if (userInfo != null) {
                log.info(userInfo.getUserId());
                request.getSession().setAttribute("activeuser", userInfo);
                return Collections.singletonMap("response", userInfo.getUserId());
            }
        } catch (Exception e) {
            throw new ApiRequestException("error while getting user info", HttpStatus.NO_CONTENT, e);
        }
        return Collections.singletonMap("response", "user not found");

    }

    @GetMapping(value = "/api/AllUsersLogin")
    public Collection<UserLoginInformation> AllUsersLogin(@RequestBody UserLoginInformation userInfo) {
        return loginServiceImpl.getAllSingedUpUsers();
    }
}