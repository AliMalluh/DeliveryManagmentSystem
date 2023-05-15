package com.example.deliverymanagmentsystem.dao.storerepo;

import com.example.deliverymanagmentsystem.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class StoreRepoImpl implements StoreRepo {
private static final String INSERT_STORE_QUERY = "INSERT INTO store(name, region_id, region_name, district_id, district_name, street, zip_code) "
        + "VALUES (:name, :regionId, :regionName, :districtId, :districtName, :street, :zipCode)";
private static final String GET_STORE_BY_ID_QUERY = "SELECT * FROM store WHERE id=:id";
    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Store findById(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcOperations.queryForObject(GET_STORE_BY_ID_QUERY, paramMap, storeMapper);
    }

    @Override
    public Store save(Store store) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", store.getName());
        params.addValue("regionId", store.getAddress().getRegion().getRegionId());
        params.addValue("regionName", store.getAddress().getRegion().getRegionName());
        params.addValue("districtId", store.getAddress().getDistrict().getDistrictId());
        params.addValue("districtName", store.getAddress().getDistrict().getDistrictName());
        params.addValue("street", store.getAddress().getDistrict().getStreet());
        params.addValue("zipCode", store.getAddress().getDistrict().getZipCode());
        jdbcOperations.update(INSERT_STORE_QUERY, params);
        return store;
    }
}
