package com.example.deliverymanagmentsystem.dao.userrepo;

import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.model.Store;
import com.example.deliverymanagmentsystem.model.user.Roles;
import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.storeservice.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepoImpl implements UserRepo {
    private static final String INSERT_USER_QUERY = "INSERT INTO user(firstname,lastname,role,store) VALUES(:firstname,:lastname,:role,:storeId)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET firstname=:firstname, lastname=:lastname, role=:role, store=:storeId WHERE ID=:id";
    //    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE ID=:id";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM user LEFT JOIN store ON user.store = store.id WHERE user.id =:id ";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE ID=:id";
    private static final String GET_USERS_QUERY = "SELECT * FROM user";
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StoreService storeService;

    @Override
    public User save(User user) {
        if (user.getFirstname().isEmpty()) throw new ResourceNotFoundException("User First Name Can Not Be Empty!");
        SqlParameterSource namedParameters;

        if (user.getRole().toString() == "CLIENT") {
            namedParameters = new MapSqlParameterSource("firstname", user.getFirstname())
                    .addValue("lastname", user.getLastname())
                    .addValue("role", user.getRole().toString())
                    .addValue("storeId", user.getStoreId());
        } else {
            namedParameters = new MapSqlParameterSource("firstname", user.getFirstname())
                    .addValue("lastname", user.getLastname())
                    .addValue("role", user.getRole().toString())
                    .addValue("storeId", null);
        }
        namedParameterJdbcOperations.update(INSERT_USER_QUERY, namedParameters);
        return user;
    }

    @Override
    public User update(User user) {
        SqlParameterSource namedParameters;
        if (user.getRole().toString().equals("CLIENT")) {
            namedParameters = new MapSqlParameterSource("firstname", user.getFirstname())
                    .addValue("lastname", user.getLastname())
                    .addValue("role", user.getRole().toString())
                    .addValue("storeId", user.getStoreId())
                    .addValue("id", user.getId());
        } else {
            namedParameters = new MapSqlParameterSource("firstname", user.getFirstname())
                    .addValue("lastname", user.getLastname())
                    .addValue("role", user.getRole().toString())
                    .addValue("storeId", null)
                    .addValue("id", user.getId());
        }
        namedParameterJdbcOperations.update(UPDATE_USER_BY_ID_QUERY, namedParameters);
        return user;
    }

    @Override
    public Optional<User> getById(long id) {
//        SqlParameterSource namedParameters= new MapSqlParameterSource ("id", id);
//        Optional<User> user = Optional.ofNullable(namedParameterJdbcOperations.queryForObject(GET_USER_BY_ID_QUERY,
//                namedParameters,
//                (rs, rowNum) ->
//                        new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
//                                Roles.valueOf(rs.getString("role")),rs.getInt("storeId"),)));
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Optional<User> user = Optional.ofNullable(namedParameterJdbcOperations.queryForObject(GET_USER_BY_ID_QUERY, paramMap, userMapper));
        System.out.println("sssss");
        if (user.isEmpty()) throw new ResourceNotFoundException("No employees available");
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows;
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        rows = namedParameterJdbcOperations.queryForList(GET_USERS_QUERY, namedParameters);

        for (Map row : rows) {
            User obj = new User();
            obj.setId((int) row.get("id"));
            obj.setFirstname((String) row.get("firstname"));
            obj.setLastname((String) row.get("lastname"));
            obj.setRole(Roles.valueOf((String) row.get("role")));
            if (obj.getRole().toString().equals("CLIENT")) {
                obj.setStoreId((long) row.get("store"));
                Store store = storeService.get(obj.getId());
                obj.setStore(store);
            }
            users.add(obj);
        }
        if (users.isEmpty()) throw new ResourceNotFoundException("No Users Available!");
        return users;
    }

    @Override
    public void deleteById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcOperations.update(DELETE_USER_BY_ID_QUERY, namedParameters);
    }
}
