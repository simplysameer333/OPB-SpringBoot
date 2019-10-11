package com.banking.opb.service;

import com.banking.opb.domain.UserLoginInformation;

public interface LoginService {
    String singedUpUser(UserLoginInformation userInfo);
    UserLoginInformation login(UserLoginInformation userInfo) ;

}
