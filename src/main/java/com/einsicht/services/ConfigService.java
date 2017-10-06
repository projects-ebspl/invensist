package com.einsicht.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.einsicht.dao.ConfigDao;
import com.einsicht.dao.InventoryDao;
import com.einsicht.entities.Store;
import com.einsicht.entities.User;
import com.einsicht.enums.StoreType;
import com.einsicht.models.ResetPassword;
import com.einsicht.models.StoreModel;
import com.einsicht.models.UserModel;
import com.einsicht.models.UserStoreModel;

@Service
public class ConfigService {

	@Autowired
	ConfigDao configDao;
	@Autowired
	InventoryDao inventoryDao;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * save new user along with assigned roles
	 * @param userModel
	 */
	public boolean saveUser(UserModel userModel) {		
		User user = this.toUser(userModel);
		user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
		configDao.saveUser(user);
		return true;
	}

	/**
	 * get all users
	 * @return
	 */
	public List<UserModel> getUsers() {		
		List<User> users = configDao.getAllUsers();
		List<UserModel> userModels = new ArrayList<>();
		for(User user:users){			
			UserModel userModel = this.toUserModel(user);
			userModels.add(userModel);
		}
		return userModels;
	}
	/**
	 * delete list of  users
	 * @param list of id 
	 * @return
	 */
	public void deleteUsers(String stringIds) {
		String [] ids = stringIds.split(",");
		for(String id:ids){
			this.deleteUser(Integer.parseInt(id));
		}		
	}
	/**
	 * @param email
	 * @return
	 */
	public UserModel getUserByEmail(String email) {

		User user = configDao.getUserByEmail(email);

		return this.toUserModel(user);
	}
	/**
	 * @param id
	 * @return
	 */
	public UserModel getUserById(int id) {
		User user = configDao.getUserById(id);
		
		return this.toUserModel(user);
	}

	private UserModel toUserModel(User user){

		if(user == null){
			return null;
		}
		UserModel userModel = new UserModel();
		userModel.setAddress(user.getAddress());
		userModel.setId(user.getId());
		userModel.setEmail(user.getEmail());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setPassword(user.getPassword());
		userModel.setPhone(user.getPhone());
		userModel.setAdmin(user.isAdmin());
		userModel.setPlanner(user.isPlanner());
		userModel.setUser(user.isUser());

		return userModel;
	}
	
	private User toUser(UserModel userModel){

		if(userModel == null){
			return null;
		}
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

		return user;
	}
	
	/**
	 * delete user for given id
	 * @param id
	 */
	public void deleteUser(int id){
		configDao.deleteUserById(id);
	}
	
	/*
	 * Need to add test-case
	 */
	public List<StoreModel> getAllStores() {
		List<Store> stores = inventoryDao.getAllStores();
		ArrayList<StoreModel> models = new ArrayList<StoreModel>();
		for (Store store : stores) {
			StoreModel model = new StoreModel();
			model.setId(store.getId());
			model.setName(store.getName());
			model.setType(store.getType());
			models.add(model);
		}
		return models;
	}
	
	public List<StoreModel> getStoresForUser(int userId) {
		ArrayList<StoreModel> stores = new ArrayList<StoreModel>();
		for (int i = 1; i <= 5; i++) {
			StoreModel store = new StoreModel();
			store.setId(1);
			store.setName("STORE-" + i + "-" + userId);
			store.setType(StoreType.regular);
			stores.add(store);
		}
		return stores;
	}

	public void setDao(ConfigDao dao) {
		this.configDao = dao;
		
	}

	public boolean resetPassword(String email, String password) {
		this.configDao.resetPassword(email, password);
		return true;
		
	}
	
	public List<UserStoreModel> getUserStoreAssignments() {
		ArrayList<UserStoreModel> models = new ArrayList<UserStoreModel>();
		List<UserModel> users = getUsers();
		users.stream().forEach(user -> {
			UserStoreModel model = new UserStoreModel();
			model.setUser(user);
			model.setStores(getStoresForUser(user.getId()));
			models.add(model);
		});
		return models;
	}
}
