package com.einsicht.dao;

import java.util.List;

import org.junit.Test;

import com.einsicht.entities.Store;
import com.einsicht.enums.StoreType;

import static org.junit.Assert.*;

public class InventoryDaoTest extends BaseDaoTest {
	
	@Test
	public void testGetAllStores() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);

		List<Store> stores = dao.getAllStores();
		assertNotNull(stores);
		assertEquals(5, stores.size());
		
		assertEquals(1, stores.get(0).getId());
		assertEquals("Main", stores.get(0).getName());
		assertEquals(StoreType.regular, stores.get(0).getType());
		
		assertEquals(2, stores.get(1).getId());
		assertEquals("Reject-1", stores.get(1).getName());
		assertEquals(StoreType.rejection, stores.get(1).getType());
		
		assertEquals(3, stores.get(2).getId());
		assertEquals("Assembly-1", stores.get(2).getName());
		assertEquals(StoreType.assembly, stores.get(2).getType());
		
		assertEquals(4, stores.get(3).getId());
		assertEquals("Wastage-1", stores.get(3).getName());
		assertEquals(StoreType.wastage, stores.get(3).getType());
		
		assertEquals(5, stores.get(4).getId());
		assertEquals("Shortage-1", stores.get(4).getName());
		assertEquals(StoreType.shortage, stores.get(4).getType());
	}


	@Test
	public void testGetStoresForUser() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);

		List<Store> stores = dao.getStoresForUser(2);
		assertNotNull(stores);
		assertEquals(3, stores.size());
		
		assertEquals(1, stores.get(0).getId());
		assertEquals("Main", stores.get(0).getName());
		assertEquals(StoreType.regular, stores.get(0).getType());
		
		assertEquals(3, stores.get(1).getId());
		assertEquals("Assembly-1", stores.get(1).getName());
		assertEquals(StoreType.assembly, stores.get(1).getType());

		assertEquals(5, stores.get(2).getId());
		assertEquals("Shortage-1", stores.get(2).getName());
		assertEquals(StoreType.shortage, stores.get(2).getType());
	}
}
