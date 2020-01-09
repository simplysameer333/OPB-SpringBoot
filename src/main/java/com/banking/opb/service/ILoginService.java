package com.banking.opb.service;

import java.util.Collection;

import com.banking.opb.domain.custom.UserLoginInformation;

public interface ILoginService {
    String singedUpUser(UserLoginInformation userInfo);
    String userOTPAuth(UserLoginInformation userInfo);
    UserLoginInformation login(UserLoginInformation userInfo);
    String kycUpdate(UserLoginInformation userInfo);
    UserLoginInformation kycDetails(String email);

}
