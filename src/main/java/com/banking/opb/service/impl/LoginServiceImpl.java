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
import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.service.ILoginService;
import com.banking.opb.utilities.BasicUtilities;
import com.banking.opb.utilities.ConfigProperties;

@Service
public class LoginServiceImpl implements ILoginService {
    private static Map<String, UserLoginInformation> userCache = new HashMap<>();

    @Autowired
    private ConfigProperties properties;

    @Autowired
    private DirectAuthenticationClient directAuthenticationClient;

    public String singedUpUser(UserLoginInformation userInfo) {
        if (BasicUtilities.isEmptyOrNullString(userInfo.getEmail()) || BasicUtilities.isEmptyOrNullString(userInfo.getUsername())
                || BasicUtilities.isEmptyOrNullCharaterArray(userInfo.getPassword()))
            return "MandatoryMissing";

        if (userCache.containsKey(userInfo.getUsername()))
            return "UserExists";

        userInfo.setUserId("user_".concat(String.valueOf(userCache.size()+1)));
        userCache.put(userInfo.getUsername(), userInfo);
        return userInfo.getUsername();
    }

    public UserLoginInformation login(UserLoginInformation userInfo) {
        UserLoginInformation currentUser = userCache.get(userInfo.getUsername());
        if (currentUser != null
                && String.copyValueOf(currentUser.getPassword()).equals(String.copyValueOf(userInfo.getPassword()))) {
            String authToken = directAuthenticationClient.login(properties.getConfigValue("obp.username"),
                    properties.getConfigValue("obp.password"),
                    properties.getConfigValue("obp.consumerKey"));
            currentUser.setAuthToken(authToken);
            SecurityContextHolder.setContext(new SecurityContextImpl(
                    new UsernamePasswordAuthenticationToken(userInfo.getUsername(), authToken)));
            return currentUser;
        }
        return null;
    }

    public Collection<UserLoginInformation> getAllSingedUpUsers() {
        return userCache.values();
    }
}
