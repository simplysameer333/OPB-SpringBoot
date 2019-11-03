package com.banking.opb.repo;

import java.util.Collection;

import com.banking.opb.domain.custom.UserLoginInformation;

public interface IUserDao {
	String registerUser(UserLoginInformation userInfo);
	UserLoginInformation login(UserLoginInformation userInfo);
}
