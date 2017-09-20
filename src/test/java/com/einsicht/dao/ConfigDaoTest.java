package com.einsicht.dao;

import java.util.List;

import org.junit.Test;

import com.einsicht.entities.Role;
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
		
		assertNotNull(users.get(0).getRoles());
		assertEquals(1, users.get(0).getRoles().length);
		assertEquals(1, users.get(0).getRoles()[0].getId());
		assertEquals("Admin", users.get(0).getRoles()[0].getName());
		assertEquals("Administrator", users.get(0).getRoles()[0].getDescription());
		
		assertEquals(2, users.get(1).getId());
		assertEquals("Mayuresh", users.get(1).getFirstName());
		assertEquals("Halshikar", users.get(1).getLastName());
		assertEquals("mayuresh@einsicht.com", users.get(1).getEmail());
		assertEquals("1234", users.get(1).getPhone());
		assertEquals("----", users.get(1).getAddress());

		assertNotNull(users.get(1).getRoles());
		assertEquals(1, users.get(1).getRoles().length);
		assertEquals(2, users.get(1).getRoles()[0].getId());
		assertEquals("Planner", users.get(1).getRoles()[0].getName());
		assertEquals("Planning invoices", users.get(1).getRoles()[0].getDescription());
	}


	@Test
	public void testGetUserByEmail() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		User user = dao.getUserByEmail("mayuresh@einsicht.com");
		assertNotNull(user);
		assertEquals(2, user.getId());
		assertEquals("Mayuresh", user.getFirstName());
		assertEquals("Halshikar", user.getLastName());
		assertEquals("mayuresh@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("----", user.getAddress());

		assertNotNull(user.getRoles());
		assertEquals(1, user.getRoles().length);
		assertEquals(2, user.getRoles()[0].getId());
		assertEquals("Planner", user.getRoles()[0].getName());
		assertEquals("Planning invoices", user.getRoles()[0].getDescription());
	}

	@Test
	public void testGetUserById() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		User user = dao.getUserById(2);
		assertNotNull(user);
		assertEquals(2, user.getId());
		assertEquals("Mayuresh", user.getFirstName());
		assertEquals("Halshikar", user.getLastName());
		assertEquals("mayuresh@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("----", user.getAddress());

		assertNotNull(user.getRoles());
		assertEquals(1, user.getRoles().length);
		assertEquals(2, user.getRoles()[0].getId());
		assertEquals("Planner", user.getRoles()[0].getName());
		assertEquals("Planning invoices", user.getRoles()[0].getDescription());
	}

	@Test
	public void testGetRoles() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		List<Role> roles = dao.getRoles();
		assertNotNull(roles);
		assertEquals(3, roles.size());
		
		assertEquals(1, roles.get(0).getId());
		assertEquals("Admin", roles.get(0).getName());
		assertEquals("Administrator", roles.get(0).getDescription());

		assertEquals(2, roles.get(1).getId());
		assertEquals("Planner", roles.get(1).getName());
		assertEquals("Planning invoices", roles.get(1).getDescription());

	
		assertEquals(3, roles.get(2).getId());
		assertEquals("User", roles.get(2).getName());
		assertEquals("Regular user", roles.get(2).getDescription());
	}

	@Test
	public void testGetRolesForUser() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		List<Role> roles = dao.getRolesForUser(2);
		assertNotNull(roles);
		assertEquals(1, roles.size());
		
		assertEquals(2, roles.get(0).getId());
		assertEquals("Planner", roles.get(0).getName());
		assertEquals("Planning invoices", roles.get(0).getDescription());
	}
}
