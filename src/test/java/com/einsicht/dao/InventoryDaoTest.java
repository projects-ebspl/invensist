package com.einsicht.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.einsicht.entities.Store;
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

}
