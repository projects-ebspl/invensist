package com.einsicht.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class BaseDao extends JdbcDaoSupport {
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected void deleteObjectFromTableById(String table, Object id) {
		String sql = "delete from " + table + " where id = ?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
}
