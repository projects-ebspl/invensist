package com.einsicht.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.einsicht.entities.Role;
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
	
	public User getUserByEmail(String email) {
		String sql = "select id as userid, firstname, lastname, email, phone, address from Users where email = '" + email + "'";
		return getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				return populateUser(rs, new User());
			}});
	}
	
	public User getUserById(int id) {
		String sql = "select id as userid, firstname, lastname, email, phone, address from Users where id = " + id;
		return getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				return populateUser(rs, new User());
			}});
	}
	
	public List<Role> getRoles() {
		String sql = "select id as roleid, name, description from Roles";
		return getJdbcTemplate().query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int arg1) throws SQLException {
				return populateRole(rs, new Role());
			}});
	}

	public List<Role> getRolesForUser(int id) {
		String sql = "select id as roleid, name, description from Roles where id in (select role from UserRoleMapping where user = " + id + ")";
		return getJdbcTemplate().query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int arg1) throws SQLException {
				return populateRole(rs, new Role());
			}});
	}
	
	private Role populateRole(ResultSet rs, Role role) throws SQLException {
		role.setId(rs.getInt("roleid"));
		role.setDescription(rs.getString("description"));
		role.setName((rs.getString("name")));
		return role;
	}

	private User populateUser(ResultSet rs, User user) throws SQLException {
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("firstName"));
		user.setId(rs.getInt("userid"));
		user.setLastName(rs.getString("lastName"));
		user.setPhone(rs.getString("phone"));
		List<Role> roles = getRolesForUser(rs.getInt("userid"));
		user.setRoles(roles.toArray(new Role[roles.size()]));
		return user;
	}
	
}
