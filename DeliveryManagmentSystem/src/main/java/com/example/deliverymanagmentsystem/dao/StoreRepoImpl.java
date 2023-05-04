package com.example.deliverymanagmentsystem.dao;

import com.example.deliverymanagmentsystem.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class StoreRepoImpl implements StoreRepo {
    private static final String INSERT_STORE_QUERY = "INSERT INTO store(name,country, city, street, zip_code) "
            + "VALUES (:name, :country, :street, :city, :zipCode)";
    private static final String GET_STORE_BY_ID_QUERY = "SELECT * FROM store WHERE id = :id";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Store findById(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(GET_STORE_BY_ID_QUERY, paramMap, storeMapper);
    }

    @Override
    public Store save(Store store) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", store.getName());
        params.addValue("country", store.getAddress().getCountry());
        params.addValue("city", store.getAddress().getCity());
        params.addValue("street", store.getAddress().getStreet());
        params.addValue("zipCode", store.getAddress().getZipCode());
        jdbcTemplate.update(INSERT_STORE_QUERY, params);
        return store;
    }
}
