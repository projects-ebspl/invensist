package com.einsicht.services;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncriptDescriptTest {
	
	@Test
	public void getEncriptedPassword(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String password = bCryptPasswordEncoder.encode("admin123456");
		System.out.println(password);
		
	}

}
