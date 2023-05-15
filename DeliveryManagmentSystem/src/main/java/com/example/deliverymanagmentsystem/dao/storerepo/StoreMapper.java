package com.example.deliverymanagmentsystem.dao.storerepo;

import com.example.deliverymanagmentsystem.model.Store;
import com.example.deliverymanagmentsystem.model.address.Address;
import com.example.deliverymanagmentsystem.model.address.District;
import com.example.deliverymanagmentsystem.model.address.Region;
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
        Region region = new Region();
        District district = new District();
        region.setRegionId(rs.getLong("region_id"));
        region.setRegionName(rs.getString("region_name"));
        district.setDistrictId(rs.getLong("district_id"));
        district.setDistrictName(rs.getString("district_name"));
        district.setStreet(rs.getString("street"));
        district.setZipCode(rs.getString("zip_code"));
        address.setRegion(region);
        address.setDistrict(district);
        store.setAddress(address);
        return store;
    }
}
