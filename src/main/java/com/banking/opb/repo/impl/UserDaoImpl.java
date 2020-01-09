package com.banking.opb.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.repo.IUserDao;
import com.banking.opb.repo.SqlQueries;
import com.banking.opb.repo.rowmapper.UserInfoRowMapper;

@Repository
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private SqlQueries queries;

	@Override
	public String registerUser(UserLoginInformation userInfo) {
		
		String sqlquery = queries.getQueries().get("checkUser");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("username", userInfo.getUsername())
				.addValue("email", userInfo.getEmail().toLowerCase())
				.addValue("phone", userInfo.getPhone());
		
		
		int count  = namedParameterJdbcTemplate.queryForObject(sqlquery, namedParameters, Integer.class);
		if (count > 0)
			return "UserExists";
		
		sqlquery = queries.getQueries().get("createUser");
		namedParameters = new MapSqlParameterSource()
				.addValue("username", userInfo.getUsername())
				.addValue("password", String.copyValueOf(userInfo.getPassword()))
				.addValue("email", userInfo.getEmail().toLowerCase())
				.addValue("phone", userInfo.getPhone());
		
		count = namedParameterJdbcTemplate.update(sqlquery, namedParameters);
		if (count > 0)
			return userInfo.getUsername();
		
		return null;
	}

	@Override
	public UserLoginInformation login(UserLoginInformation userInfo) {
		
		String sqlquery = queries.getQueries().get("loginVerify");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("email", userInfo.getEmail().toLowerCase())
				.addValue("password", String.valueOf(userInfo.getPassword()));
		
		
		List<UserLoginInformation> userList  = namedParameterJdbcTemplate.query(sqlquery, namedParameters,new UserInfoRowMapper());
		if (userList != null && userList.size()==1)
			return userList.get(0);
		
		return null;
	}

	@Override
	public String activateUser(UserLoginInformation userInfo) {
		String sqlquery = queries.getQueries().get("activateUser");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("email", userInfo.getEmail().toLowerCase());
		int count = namedParameterJdbcTemplate.update(sqlquery, namedParameters);
		if (count > 0)
			return userInfo.getEmail();
		return null;
	}
	
	@Override
	public String kycUpdateUser(UserLoginInformation userInfo) {
		String sqlquery = queries.getQueries().get("kycUpdateUser");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("passport", userInfo.getPassport())
				.addValue("drivinglicense", userInfo.getDrivingLicense())
				.addValue("email", userInfo.getEmail().toLowerCase());
		int count = namedParameterJdbcTemplate.update(sqlquery, namedParameters);
		if (count > 0)
			return userInfo.getEmail();
		return null;
	}
	
	@Override
	public UserLoginInformation kycUserDetails(String email) {
		String sqlquery = queries.getQueries().get("kycUser");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("email", email.toLowerCase());
		
		List<UserLoginInformation> userList  = namedParameterJdbcTemplate.query(sqlquery, namedParameters,new UserInfoRowMapper());
		if (userList != null && userList.size()==1)
			return userList.get(0);
		return null;
	}

}
