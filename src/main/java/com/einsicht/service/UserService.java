package com.einsicht.service;

import com.einsicht.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
