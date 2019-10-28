package com.banking.opb.service;

import com.banking.opb.domain.custom.UserLoginInformation;

import java.util.Collection;

public interface ILoginService {
    String singedUpUser(UserLoginInformation userInfo);
    UserLoginInformation login(UserLoginInformation userInfo) ;
    Collection<UserLoginInformation> getAllSingedUpUsers();
}
