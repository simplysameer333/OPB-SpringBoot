package com.banking.opb.api;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.exception.ApiRequestException;
import com.banking.opb.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping(value = "/api/signUpUser", consumes = "application/json", produces = "application/json")
    public Map<String, String> signUp(@RequestBody UserLoginInformation userInfo) {
        String response = "Failed";
        String username = loginService.singedUpUser(userInfo);
        if (username != null && !"MandatoryMissing".equals(username) && !"UserExists".equals(username))
            response = "Success";
        return Collections.singletonMap("response", response);
    }

    @PostMapping(value = "/api/loginUser", consumes = "application/json", produces = "application/json")
    public Map<String, String> login(@RequestBody UserLoginInformation userInfo) {
        try {
            userInfo = loginService.login(userInfo);
            if (userInfo != null) {
                log.info(userInfo.getUserId());
                BasicUtilities.session().setAttribute("activeuser", userInfo);
                return Collections.singletonMap("response", userInfo.getAuthToken());
            }
        } catch (Exception e) {
            throw new ApiRequestException("error while getting user info", HttpStatus.NO_CONTENT, e);
        }
        return Collections.singletonMap("response", "user not found");

    }

    @GetMapping(value = "/api/AllUsersLogin")
    public Collection<UserLoginInformation> AllUsersLogin(@RequestBody UserLoginInformation userInfo) {
        return loginService.getAllSingedUpUsers();
    }
}