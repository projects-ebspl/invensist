package com.einsicht.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.einsicht.entities.Store;
import com.einsicht.enums.StoreType;

public class StoreRowMapper implements RowMapper<Store> {

	@Override
	public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
		Store st = new Store();
		st.setId(rs.getInt("id"));
		st.setName(rs.getString("name"));
		st.setType(StoreType.valueOf(rs.getString("type")));
		return st;
	}
}
