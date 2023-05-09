package com.example.deliverymanagmentsystem.dao.userrepo;

import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.model.Store;
import com.example.deliverymanagmentsystem.model.user.Roles;
import com.example.deliverymanagmentsystem.model.user.User;
import com.example.deliverymanagmentsystem.service.storeservice.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Types;
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

     @Override
    public User save(User user) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameters.registerSqlType("role", Types.VARCHAR);
        namedParameterJdbcOperations.update(INSERT_USER_QUERY, namedParameters);
        return null;
    }

    @Override
    public User update(User user) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameters.registerSqlType("role", Types.VARCHAR);
        namedParameterJdbcOperations.update(UPDATE_USER_BY_ID_QUERY, namedParameters);
        return user;
    }

    @Override
    public Optional<User> getById(long id) throws IncorrectResultSizeDataAccessException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try{
            Optional<User> user = Optional.of(namedParameterJdbcOperations.queryForObject(GET_USER_BY_ID_QUERY, paramMap, userMapper));
            return user;
        } catch(IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return namedParameterJdbcOperations.query(GET_USERS_QUERY, namedParameters, new UserMapper());
    }

    @Override
    public void deleteById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcOperations.update(DELETE_USER_BY_ID_QUERY, namedParameters);
    }
}
