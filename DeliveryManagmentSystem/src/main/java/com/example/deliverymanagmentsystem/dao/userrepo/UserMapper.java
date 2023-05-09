package com.example.deliverymanagmentsystem.dao.userrepo;

import com.example.deliverymanagmentsystem.model.Store;
import com.example.deliverymanagmentsystem.model.address.Address;
import com.example.deliverymanagmentsystem.model.address.District;
import com.example.deliverymanagmentsystem.model.address.Region;
import com.example.deliverymanagmentsystem.model.user.Roles;
import com.example.deliverymanagmentsystem.model.user.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setRole(Roles.valueOf(rs.getString("role")));
        if(rs.getString("role").equals("CLIENT")){
            try {
                user.setStoreId(rs.getLong("store"));
                Store store = new Store();
                store.setId(rs.getLong("store"));
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
                user.setStore(store);
            }catch (Exception e){

            }

        }
//        Optional<User> user = Optional.of(new User());
//        user = userService.get(rs.getLong("user_id"));
//        store.setUser(user);
        return user;
    }
}
