package com.banking.opb.service;

import com.banking.opb.domain.UserLoginInformation;

import java.util.Collection;

public interface LoginService {
    String singedUpUser(UserLoginInformation userInfo);
    UserLoginInformation login(UserLoginInformation userInfo) ;

    Collection<UserLoginInformation> getAllSingedUpUsers();
}
