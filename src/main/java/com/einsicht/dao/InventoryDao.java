package com.einsicht.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.einsicht.dao.mappers.StoreRowMapper;
import com.einsicht.entities.Store;

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

	
}
