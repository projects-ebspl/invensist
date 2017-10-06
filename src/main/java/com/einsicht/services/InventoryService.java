package com.einsicht.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einsicht.dao.InventoryDao;
import com.einsicht.entities.Store;
import com.einsicht.models.StoreModel;

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
		Store store = inventoryDao.getStoreById(id);
		return this.toStoreModel(store);
	}
	public List<StoreModel> getStores() {		
		List<StoreModel> storeModels = new ArrayList<>();
		for(Store store:inventoryDao.getAllStores()){
			StoreModel userModel = this.toStoreModel(store);
			storeModels.add(userModel);
		}
		return storeModels;
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
