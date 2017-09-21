package com.einsicht.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.einsicht.entities.Item;
import com.einsicht.enums.ItemType;

public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Item()
				.setAssemblycost(rs.getDouble("assemblycost"))
				.setCode(rs.getString("code"))
				.setDescription(rs.getString("description"))
				.setId(rs.getInt("id"))
				.setItemcost(rs.getDouble("itemcost"))
				.setType(ItemType.valueOf(rs.getString("type")));
	}

}
