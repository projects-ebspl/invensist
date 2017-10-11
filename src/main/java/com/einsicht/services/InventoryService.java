package com.einsicht.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einsicht.dao.InventoryDao;
import com.einsicht.entities.Store;
import com.einsicht.models.StoreModel;
import com.einsicht.models.UserModel;
import com.einsicht.models.UserStoreModel;

@Service("inventoryService")
public class InventoryService {
	
	@Autowired
	InventoryDao inventoryDao;
	public void deleteStores(String stringIds) {
		String [] ids = stringIds.split(",");
		for(String id:ids){			
			this.deleteStore(Integer.parseInt(id));			
		}		
	}

	private void deleteStore(int id) {
		inventoryDao.deleteStoreById(id);		
	}
	public StoreModel getStore(int id) {
		return toStoreModel(inventoryDao.getStoreById(id));
	}
	public List<StoreModel> getStores() {		
		return inventoryDao.getAllStores().stream().map(store -> toStoreModel(store)).collect(Collectors.toList());
	}
	
	public List<StoreModel> getStoresForUser(Integer userId) {		
		return inventoryDao.getStoresForUser(userId).stream().map(store -> toStoreModel(store)).collect(Collectors.toList());
	}
	
	public List<UserStoreModel> getUserStoreAssignments(List<UserModel> users) {
		return users.stream().map(user -> {
			return new UserStoreModel()
			.withUser(user)
			.withStores(getStoresForUser(user.getId()));
		}).collect(Collectors.toList());
	}
	
	
	private StoreModel toStoreModel(Store store){
		if(store == null){
			return null;
		}
		StoreModel storeModel = new StoreModel();
		storeModel.setId(store.getId());
		storeModel.setName(store.getName());
		storeModel.setType(store.getType());		
		return storeModel;
	}

	public void setDao(InventoryDao dao) {
		this.inventoryDao = dao;		
	}

	public boolean addStore(StoreModel storeModel) {
		
		Store store = this.toStore(storeModel);
		inventoryDao.saveStore(store);
		return true;
		
	}
	
	public void assignStore(int userId, List<Integer> storeIds) {
		inventoryDao.assignStore(userId, storeIds);
	}

	private Store toStore(StoreModel storeModel) {
		if(storeModel == null){
			return null;
		}
		Store store = new Store();
		store.setId(storeModel.getId());
		store.setName(storeModel.getName());
		store.setType(storeModel.getType());
		return store;
	}	
}
