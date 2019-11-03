package com.banking.opb.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.repo.IUserDao;

@Service
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String registerUser(UserLoginInformation userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLoginInformation login(UserLoginInformation userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
