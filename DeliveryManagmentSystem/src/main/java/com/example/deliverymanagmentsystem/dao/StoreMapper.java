package com.example.deliverymanagmentsystem.dao;

import com.example.deliverymanagmentsystem.model.Address;
import com.example.deliverymanagmentsystem.model.Store;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StoreMapper implements RowMapper<Store> {
    @Override
    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
        Store store = new Store();
        store.setId(rs.getLong("id"));
        store.setName(rs.getString("name"));
        Address address = new Address();
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setZipCode(rs.getString("zip_code"));
        store.setAddress(address);
        return store;
    }
}
