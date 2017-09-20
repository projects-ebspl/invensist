package com.einsicht.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.einsicht.entities.User;



@Repository("configDao")
public class ConfigDao extends BaseDao {
	
	public List<User> getAllUsers() {
		String sql = "select id as userid, firstname, lastname, email, phone, address from Users order by id";
		return getJdbcTemplate().query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				populateUser(rs, user);
				return user;
			}});
	}
	
	private void populateUser(ResultSet rs, User user) throws SQLException {
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("firstName"));
		user.setId(rs.getInt("userid"));
		user.setLastName(rs.getString("lastName"));
		user.setPhone(rs.getString("phone"));
	}
}
