package com.einsicht.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.einsicht.dao.BaseTest;
import com.einsicht.dao.InventoryDao;
import com.einsicht.models.StoreModel;

public class InventoryServiceTest extends BaseTest{
	InventoryDao dao ;
	InventoryService service;

	@Before
	public void setup(){
		dao= new InventoryDao();
		super.setUp(dao);
		service = new InventoryService();
		service.setDao(dao);
	}
	@Test
	public void getStores(){
		List<StoreModel> stores  = service.getStores();
		System.out.println(stores);
	}
}
