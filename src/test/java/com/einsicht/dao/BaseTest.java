package com.einsicht.dao;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.einsicht.dao.BaseDao;

public abstract class BaseTest {
	
	protected void setUp(BaseDao dao) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:mysql://localhost/invensistest?autoReconnect=true");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("root");
		dao.setDataSource(ds);
	}
}
