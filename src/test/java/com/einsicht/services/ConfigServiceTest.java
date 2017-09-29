package com.einsicht.services;

import org.junit.Before;
import org.junit.Test;

import com.einsicht.dao.BaseTest;
import com.einsicht.dao.ConfigDao;

public class ConfigServiceTest extends BaseTest{

	ConfigDao dao ;
	ConfigService service;

	@Before
	public void setup(){
		dao= new ConfigDao();
		super.setUp(dao);
		service = new ConfigService();
		service.setDao(dao);
	}
	@Test
	public void deleteUsers(){
		service.deleteUsers("1,2");
	}
}
