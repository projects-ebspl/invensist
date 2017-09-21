package com.einsicht.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.einsicht.dao.mappers.StoreRowMapper;
import com.einsicht.entities.Store;
import com.einsicht.enums.StoreType;

@Repository("inventoryDao")
public class InventoryDao extends BaseDao {
	
	
	public List<Store> getAllStores() {
		String sql = "select id, name, type from Stores";
		return getJdbcTemplate().query(sql, new StoreRowMapper());
	}
	
	public List<Store> getStoresForUser(int userId) {
		String sql = "select S.id, S.name, S.type from Stores S, Users U, UserStoreMapping US where U.id = US.user and S.id = US.store and U.id = ?";
		return getJdbcTemplate().query(sql, new Object[] {userId}, new StoreRowMapper());
	}
	
	public List<Store> getStoresForStoreType(StoreType storeType) {
		String sql = "select id, name, type from Stores where type = ?";
		return getJdbcTemplate().query(sql, new Object[] {storeType.name()}, new StoreRowMapper());
	}

	public Store getStoresForName(String name) {
		String sql = "select id, name, type from Stores where name = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {name}, new StoreRowMapper());
	}

	public void saveStore(Store store) {
		if(store.getId() == null) {
			// Save as new 
			String sql = "insert into Stores set name = ?, type = ?";
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getType().name()});
		} else {
			// Save as update
			String sql = "update Stores set name = ?, type = ? where id = ?";
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getType().name(), store.getId()});
		}
	}
	
	public void deleteStoreById(int id) {
		String sql = "delete from Stores where id = ?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
	
}
