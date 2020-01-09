package com.banking.opb.repo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banking.opb.domain.custom.UserLoginInformation;

public class UserInfoRowMapper implements RowMapper<UserLoginInformation> {
	@Override
	public UserLoginInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserLoginInformation user = new UserLoginInformation(rs.getString("USERNAME"),
				rs.getString("PASSWORD"), rs.getString("EMAIL"), rs.getString("PHONE"),
				rs.getString("ACTIVE"), rs.getString("PASSPORT"), rs.getString("DRIVINGLICENSE"));
		return user;
	}
}