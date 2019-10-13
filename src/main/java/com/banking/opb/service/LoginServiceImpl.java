package com.banking.opb.service;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.domain.UserLoginInformation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements  LoginService {
    private static Map<String, UserLoginInformation> userCache = new HashMap<>();

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
        if (currentUser != null && currentUser.getPassword().equals(userInfo.getPassword())) {
            currentUser.setPassword(new char[]{});
            return currentUser;
        }
        return null;
    }

    public Collection<UserLoginInformation> getAllSingedUpUsers() {
        return userCache.values();
    }
}
