package com.banking.opb.repo.impl;

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
				.addValue("email", userInfo.getEmail());
		
		
		int count  = namedParameterJdbcTemplate.queryForObject(sqlquery, namedParameters, Integer.class);
		if (count > 0)
			return "UserExists";
		
		sqlquery = queries.getQueries().get("createUser");
		namedParameters = new MapSqlParameterSource()
				.addValue("username", userInfo.getUsername())
				.addValue("password", String.copyValueOf(userInfo.getPassword()))
				.addValue("email", userInfo.getEmail());
		
		count = namedParameterJdbcTemplate.update(sqlquery, namedParameters);
		if (count > 0)
			return userInfo.getUsername();
		
		return null;
	}

	@Override
	public UserLoginInformation login(UserLoginInformation userInfo) {
		
		
		return null;
	}

}
