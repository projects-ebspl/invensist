package com.einsicht.dao;

import java.util.List;

import org.junit.Test;

import com.einsicht.entities.User;

import static org.junit.Assert.*;
public class ConfigDaoTest extends BaseDaoTest {
	
	@Test
	public void testGetAllUsers() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		List<User> users = dao.getAllUsers();
		assertEquals(2, users.size());
		
		assertEquals(1, users.get(0).getId());
		assertEquals("Admin", users.get(0).getFirstName());
		assertEquals("admin", users.get(0).getLastName());
		assertEquals("admin@einsicht.com", users.get(0).getEmail());
		assertEquals("1234", users.get(0).getPhone());
		assertEquals("----", users.get(0).getAddress());
		
		assertEquals(2, users.get(1).getId());
		assertEquals("Mayuresh", users.get(1).getFirstName());
		assertEquals("Halshikar", users.get(1).getLastName());
		assertEquals("mayuresh@einsicht.com", users.get(1).getEmail());
		assertEquals("1234", users.get(1).getPhone());
		assertEquals("----", users.get(1).getAddress());
	}
}
