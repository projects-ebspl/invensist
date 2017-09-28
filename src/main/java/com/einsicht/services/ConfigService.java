package com.einsicht.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einsicht.dao.ConfigDao;
import com.einsicht.entities.User;
import com.einsicht.models.UserModel;

@Service
public class ConfigService {
	
	@Autowired
	ConfigDao configDao;
	
	/**
	 * save new user along with assigned roles
	 * @param userModel
	 */
	public boolean saveUser(UserModel userModel) {		
		 User user = new User();
		 user.setAddress(userModel.getAddress());
		 user.setEmail(userModel.getEmail());
		 user.setFirstName(userModel.getFirstName());
		 user.setLastName(userModel.getLastName());
		 user.setPassword(userModel.getPassword());
		 user.setPhone(userModel.getPhone());
		 user.setAdmin(userModel.isAdmin());
		 user.setPlanner(userModel.isPlanner());
		 user.setUser(userModel.isUser());		 
		configDao.saveUser(user);
		return true;
	}
	
	public List<UserModel> getUsers() {		
		List<User> users = configDao.getAllUsers();
		List<UserModel> userModels = new ArrayList<>();
		for(User user:users){			
			UserModel userModel = new UserModel();			
			userModel.setAddress(user.getAddress());
			userModel.setEmail(user.getEmail());
			userModel.setFirstName(user.getFirstName());
			userModel.setLastName(user.getLastName());
			userModel.setPassword(user.getPassword());
			userModel.setPhone(user.getPhone());
			userModel.setAdmin(user.isAdmin());
			userModel.setPlanner(user.isPlanner());
			userModel.setUser(user.isUser());
			userModels.add(userModel);
		}
		return userModels;
	}
}
