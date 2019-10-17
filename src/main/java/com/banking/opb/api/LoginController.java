package com.banking.opb.api;

import com.banking.opb.domain.UserLoginInformation;
import com.banking.opb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private LoginService loginServiceImpl;

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
        userInfo = loginServiceImpl.login(userInfo);
        LOGGER.info(userInfo.getUserId());
        if (userInfo != null)
            request.getSession().setAttribute("activeuser", userInfo);
        return Collections.singletonMap("response", userInfo.getUserId());
    }

    @GetMapping(value = "/api/AllUsersLogin")
    public Collection<UserLoginInformation> AllUsersLogin(@RequestBody UserLoginInformation userInfo) {
        return loginServiceImpl.getAllSingedUpUsers();
    }
}