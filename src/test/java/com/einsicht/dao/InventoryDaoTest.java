package com.einsicht.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.einsicht.entities.Item;
import com.einsicht.entities.Store;
import com.einsicht.enums.ItemType;
import com.einsicht.enums.StoreType;

public class InventoryDaoTest extends BaseDaoTest {
	
	@Test
	public void testGetAllStores() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);

		List<Store> stores = dao.getAllStores();
		assertNotNull(stores);
		assertEquals(5, stores.size());
		
		assertEquals(1, stores.get(0).getId().intValue());
		assertEquals("Main", stores.get(0).getName());
		assertEquals(StoreType.regular, stores.get(0).getType());
		
		assertEquals(2, stores.get(1).getId().intValue());
		assertEquals("Reject-1", stores.get(1).getName());
		assertEquals(StoreType.rejection, stores.get(1).getType());
		
		assertEquals(3, stores.get(2).getId().intValue());
		assertEquals("Assembly-1", stores.get(2).getName());
		assertEquals(StoreType.assembly, stores.get(2).getType());
		
		assertEquals(4, stores.get(3).getId().intValue());
		assertEquals("Wastage-1", stores.get(3).getName());
		assertEquals(StoreType.wastage, stores.get(3).getType());
		
		assertEquals(5, stores.get(4).getId().intValue());
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
		
		assertEquals(1, stores.get(0).getId().intValue());
		assertEquals("Main", stores.get(0).getName());
		assertEquals(StoreType.regular, stores.get(0).getType());
		
		assertEquals(3, stores.get(1).getId().intValue());
		assertEquals("Assembly-1", stores.get(1).getName());
		assertEquals(StoreType.assembly, stores.get(1).getType());

		assertEquals(5, stores.get(2).getId().intValue());
		assertEquals("Shortage-1", stores.get(2).getName());
		assertEquals(StoreType.shortage, stores.get(2).getType());
	}
	
	@Test
	public void testGetStoresForStoreType() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		
		List<Store> stores = dao.getStoresForStoreType(StoreType.assembly);
		assertNotNull(stores);
		assertEquals(1, stores.size());
		assertEquals(3, stores.get(0).getId().intValue());
		assertEquals("Assembly-1", stores.get(0).getName());
		assertEquals(StoreType.assembly, stores.get(0).getType());
	}

	@Test
	public void testSaveStore() throws Exception {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		try {
			dao.saveStore(new Store().setName("Main").setType(StoreType.regular));
		} catch (DuplicateKeyException e) {
			// We except this exception. So its ok.
		}
		dao.saveStore(new Store().setName("MainNxt").setType(StoreType.regular));
		Store store = dao.getStoresForName("MainNxt");
		assertNotNull(store);
		assertEquals(StoreType.regular, store.getType());
		
		dao.deleteStoreById(store.getId());
		try {
			dao.getStoresForName("MainNxt");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}

	@Test
	public void testSaveStoreForEdit() throws Exception {
		InventoryDao dao = new InventoryDao();
		setUp(dao);

		dao.saveStore(new Store().setName("MainNxt").setType(StoreType.regular));

		Store store = dao.getStoresForName("MainNxt");
		assertNotNull(store);
		assertEquals(StoreType.regular, store.getType());
		
		store.setType(StoreType.rejection);
		dao.saveStore(store);
		store = dao.getStoresForName("MainNxt");
		assertNotNull(store);
		assertEquals(StoreType.rejection, store.getType());
		
		dao.deleteStoreById(store.getId());
		try {
			dao.getStoresForName("MainNxt");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}
	
	@Test
	public void testAssignStoreToUser() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		try {
			dao.assignStoreToUser(4, 3);
		} catch (DataIntegrityViolationException e) {
			// We expect this exception
		}
		List<Store> stores = dao.getStoresForUser(2);
		assertNotNull(stores);
		assertEquals(3, stores.size());
		dao.assignStoreToUser(2, 2);
		stores = dao.getStoresForUser(2);
		assertNotNull(stores);
		assertEquals(4, stores.size());
		dao.removeStoreFromUser(2, 2);
		stores = dao.getStoresForUser(2);
		assertNotNull(stores);
		assertEquals(3, stores.size());
	}
	
	@Test
	public void testGetAllItems() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		List<Item> items = dao.getAllItems();
		assertNotNull(items);
		assertEquals(6, items.size());
		
		assertEquals(1, items.get(0).getId().intValue());
		assertEquals("S001", items.get(0).getCode());
		assertEquals("Test-1", items.get(0).getDescription());
		assertEquals(40, items.get(0).getItemcost(),0.01);
		assertEquals(0, items.get(0).getAssemblycost(), 0.01);
		assertEquals(ItemType.single, items.get(0).getType());
		
		assertEquals(2, items.get(1).getId().intValue());
		assertEquals("S002", items.get(1).getCode());
		assertEquals("Test-2", items.get(1).getDescription());
		assertEquals(35, items.get(1).getItemcost(),0.01);
		assertEquals(0, items.get(1).getAssemblycost(), 0.01);
		assertEquals(ItemType.single, items.get(1).getType());
		
		assertEquals(3, items.get(2).getId().intValue());
		assertEquals("S003", items.get(2).getCode());
		assertEquals("Test-3", items.get(2).getDescription());
		assertEquals(37, items.get(2).getItemcost(),0.01);
		assertEquals(0, items.get(2).getAssemblycost(), 0.01);
		assertEquals(ItemType.single, items.get(2).getType());
		
		assertEquals(4, items.get(3).getId().intValue());
		assertEquals("C001", items.get(3).getCode());
		assertEquals("TestC-1", items.get(3).getDescription());
		assertEquals(87, items.get(3).getItemcost(),0.01);
		assertEquals(5, items.get(3).getAssemblycost(),0.01);
		assertEquals(ItemType.combo, items.get(3).getType());
		
		assertEquals(5, items.get(4).getId().intValue());
		assertEquals("C002", items.get(4).getCode());
		assertEquals("TestC-2", items.get(4).getDescription());
		assertEquals(104, items.get(4).getItemcost(),0.01);
		assertEquals(7, items.get(4).getAssemblycost(),0.01);
		assertEquals(ItemType.combo, items.get(4).getType());
		
		assertEquals(6, items.get(5).getId().intValue());
		assertEquals("C003", items.get(5).getCode());
		assertEquals("TestC-3", items.get(5).getDescription());
		assertEquals(146, items.get(5).getItemcost(),0.01);
		assertEquals(13, items.get(5).getAssemblycost(),0.01);
		assertEquals(ItemType.combo, items.get(5).getType());
	}
	
	@Test
	public void testGetItemByCode() {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		
		try {
			dao.getItemByCode("AAA");
			fail("We expect exception here");
		} catch (EmptyResultDataAccessException e) {
			// We expect this. Don't worry
		}
		
		Item item = dao.getItemByCode("S001");
		assertEquals(1, item.getId().intValue());
		assertEquals("S001", item.getCode());
		assertEquals("Test-1", item.getDescription());
		assertEquals(40, item.getItemcost(),0.01);
		assertEquals(0, item.getAssemblycost(), 0.01);
		assertEquals(ItemType.single, item.getType());
	}

	@Test
	public void testSaveItem() throws Exception {
		InventoryDao dao = new InventoryDao();
		setUp(dao);
		try {
			dao.saveItem(new Item().setCode("S001").setDescription("AAA").setAssemblycost(2d).setItemcost(20d).setType(ItemType.combo));
		} catch (DuplicateKeyException e) {
			// We except this exception. So its ok.
		}
		dao.saveItem(new Item().setCode("SXX1").setDescription("AAA").setAssemblycost(2d).setItemcost(20d).setType(ItemType.combo));
		Item item = dao.getItemByCode("SXX1");
		assertNotNull(item);
		assertEquals("AAA", item.getDescription());
		assertEquals(2d, item.getAssemblycost(), 0.01);
		assertEquals(20d, item.getItemcost(), 0.01);
		assertEquals(ItemType.combo, item.getType());
		
		dao.deleteItemById(item.getId());
		try {
			dao.getItemByCode("SXX1");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}

	@Test
	public void testSaveItemForEdit() throws Exception {
		InventoryDao dao = new InventoryDao();
		setUp(dao);

		dao.saveItem(new Item().setCode("SXX1").setDescription("AAA").setAssemblycost(2d).setItemcost(20d).setType(ItemType.combo));

		Item item = dao.getItemByCode("SXX1");
		assertNotNull(item);
		assertEquals("AAA", item.getDescription());
		assertEquals(2d, item.getAssemblycost(), 0.01);
		assertEquals(20d, item.getItemcost(), 0.01);
		assertEquals(ItemType.combo, item.getType());
		
		item.setDescription("BBB");
		dao.saveItem(item);
		item = dao.getItemByCode("SXX1");
		assertNotNull(item);
		assertEquals("BBB", item.getDescription());
		
		dao.deleteItemById(item.getId());
		try {
			dao.getItemByCode("SXX1");
			fail("We except EmptyResultDataAccessException over here");
		} catch (EmptyResultDataAccessException e) {
			// We except this exception. Don't worry.
		}
	}
}
