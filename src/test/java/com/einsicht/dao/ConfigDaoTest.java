package com.einsicht.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.einsicht.entities.User;
public class ConfigDaoTest extends BaseTest {
	
	@Test
	public void testGetAllUsers() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		List<User> users = dao.getAllUsers();
		assertEquals(2, users.size());
		
		assertEquals(1, users.get(0).getId().intValue());
		assertEquals("Admin", users.get(0).getFirstName());
		assertEquals("admin", users.get(0).getLastName());
		assertEquals("admin@einsicht.com", users.get(0).getEmail());
		assertEquals("1234", users.get(0).getPhone());
		assertEquals("----", users.get(0).getAddress());
		
		
		assertEquals(true, users.get(0).isAdmin());
		assertEquals(true, users.get(0).isPlanner());		
		
		assertEquals(2, users.get(1).getId().intValue());
		assertEquals("Mayuresh", users.get(1).getFirstName());
		assertEquals("Halshikar", users.get(1).getLastName());
		assertEquals("mayuresh@einsicht.com", users.get(1).getEmail());
		assertEquals("1234", users.get(1).getPhone());
		assertEquals("----", users.get(1).getAddress());	
	}


	@Test
	public void testGetUserByEmail() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		User user = dao.getUserByEmail("mayuresh@einsicht.com");
		assertNotNull(user);
		assertEquals(2, user.getId().intValue());
		assertEquals("Mayuresh", user.getFirstName());
		assertEquals("Halshikar", user.getLastName());
		assertEquals("mayuresh@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("----", user.getAddress());
	}

	@Test
	public void testGetUserById() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		User user = dao.getUserById(2);
		assertNotNull(user);
		assertEquals(2, user.getId().intValue());
		assertEquals("Mayuresh", user.getFirstName());
		assertEquals("Halshikar", user.getLastName());
		assertEquals("mayuresh@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("----", user.getAddress());		
	}
	
	@Test
	public void testSaveUser() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		try {
			dao.saveUser(new User().setAddress("***").setEmail("mayuresh@einsicht.com").setFirstName("M")
					.setLastName("H").setPhone("1234").setAdmin(true).setPlanner(false).setUser(true));
		} catch (DuplicateKeyException e) {
			// We except this exception. So its ok.
		}
		dao.saveUser(new User().setAddress("ADD").setEmail("test@einsicht.com").setFirstName("M").setLastName("H")
				.setPhone("1234").setAdmin(false).setPlanner(false).setUser(true));
		User user = dao.getUserByEmail("test@einsicht.com");
		assertNotNull(user);
		assertEquals("M", user.getFirstName());
		assertEquals("H", user.getLastName());
		assertEquals("test@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("ADD", user.getAddress());
		
		dao.deleteUserById(user.getId());
		try {
			user = dao.getUserByEmail("test@einsicht.com");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}
	
	@Test
	public void testSaveUserForEdit() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);

		dao.saveUser(new User().setAddress("ADD").setEmail("test@einsicht.com").setFirstName("M").setLastName("H").setPhone("1234"));

		User user = dao.getUserByEmail("test@einsicht.com");
		assertNotNull(user);
		assertEquals("M", user.getFirstName());
		assertEquals("H", user.getLastName());
		assertEquals("test@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("ADD", user.getAddress());
		
		user.setAddress("ADDNEW");
		dao.saveUser(user);
		user = dao.getUserByEmail("test@einsicht.com");
		assertNotNull(user);
		assertEquals("M", user.getFirstName());
		assertEquals("H", user.getLastName());
		assertEquals("test@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("ADDNEW", user.getAddress());
		
		dao.deleteUserById(user.getId());
		try {
			user = dao.getUserByEmail("test@einsicht.com");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}

	@Test
	public void testResetPassword() throws Exception {
		ConfigDao dao = new ConfigDao();
		setUp(dao);
		try {
			dao.saveUser(new User().setAddress("***").setEmail("mayuresh@einsicht.com").setFirstName("M").setLastName("H").setPhone("1234"));
		} catch (DuplicateKeyException e) {
			// We except this exception. So its ok.
		}
		dao.saveUser(new User().setAddress("ADD").setEmail("test@einsicht.com").setFirstName("M").setLastName("H").setPhone("1234").setPassword("abc1234"));
		User user = dao.getUserByEmail("test@einsicht.com");
		assertNotNull(user);
		assertEquals("M", user.getFirstName());
		assertEquals("H", user.getLastName());
		assertEquals("test@einsicht.com", user.getEmail());
		assertEquals("1234", user.getPhone());
		assertEquals("ADD", user.getAddress());
		assertEquals("abc1234", user.getPassword());
		
		dao.resetPassword(user.getId(), "1234abc");
		user = dao.getUserById(user.getId());
		assertEquals("1234abc", user.getPassword());
		
		dao.deleteUserById(user.getId());
		try {
			user = dao.getUserByEmail("test@einsicht.com");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}
	
}
