package com.einsicht.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.einsicht.entities.Store;
import com.einsicht.enums.StoreType;

@Repository("inventoryDao")
public class InventoryDao extends BaseDao {
	
	
	public List<Store> getAllStores() {
		String sql = "select id, name, type from Stores";
		return getJdbcTemplate().query(sql, new RowMapper<Store>() {
			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				return populateStore(rs, new Store());
			}
		});
	}

	
	private Store populateStore(ResultSet rs, Store st) throws SQLException {
		st.setId(rs.getInt("id"));
		st.setName(rs.getString("name"));
		st.setType(StoreType.valueOf(rs.getString("type")));
		return st;
	}
}
