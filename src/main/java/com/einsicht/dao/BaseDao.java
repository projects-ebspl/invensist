package com.einsicht.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class BaseDao extends JdbcDaoSupport {
	
	protected void deleteObjectFromTableById(String table, Object id) {
		String sql = "delete from " + table + " where id = ?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
}
