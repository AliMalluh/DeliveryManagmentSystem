package com.example.deliverymanagmentsystem.dao;

import com.example.deliverymanagmentsystem.controller.errors.NoDataFoundException;
import com.example.deliverymanagmentsystem.controller.errors.ResourceNotFoundException;
import com.example.deliverymanagmentsystem.model.Roles;
import com.example.deliverymanagmentsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo {
    private static final String INSERT_USER_QUERY = "INSERT INTO user(firstname,lastname,role) VALUES(:firstname,:lastname,:role)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET firstname=:firstname WHERE ID=:id";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE ID=:id";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE ID=:id";
    private static final String GET_USERS_QUERY = "SELECT * FROM user";
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public User save(User user) {
        if (user.getFirstname().isEmpty()) throw new ResourceNotFoundException("User First Name Can Not Be Empty!");
        SqlParameterSource namedParameters= new MapSqlParameterSource ("firstname", user.getFirstname())
                                                                        .addValue("lastname", user.getLastname())
                                                                        .addValue("role", user.getRole().toString());
        namedParameterJdbcOperations.update(INSERT_USER_QUERY,namedParameters);
        return user;
    }

    @Override
    public User update(User user) {
        SqlParameterSource namedParameters= new MapSqlParameterSource ("firstname", user.getFirstname())
                .addValue("id", user.getId());
        namedParameterJdbcOperations.update(UPDATE_USER_BY_ID_QUERY,namedParameters);
        return user;
    }

    @Override
    public Optional<User> getById(long id) {
        SqlParameterSource namedParameters= new MapSqlParameterSource ("id", id);
        Optional<User> user = Optional.ofNullable(namedParameterJdbcOperations.queryForObject(GET_USER_BY_ID_QUERY,
                namedParameters,
                (rs, rowNum) ->
                        new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                                Roles.valueOf(rs.getString("role")))));
        if (user.isEmpty())throw new ResourceNotFoundException("No employees available");
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows;
        SqlParameterSource namedParameters= new MapSqlParameterSource ();
        rows = namedParameterJdbcOperations.queryForList(GET_USERS_QUERY,namedParameters);
        for (Map row : rows) {
            User obj = new User();

            obj.setId((int) row.get("id"));
            obj.setFirstname((String) row.get("firstname"));
            obj.setLastname((String) row.get("lastname"));
            obj.setRole(Roles.valueOf((String)row.get("role")));
            users.add(obj);
        }
        if(users.isEmpty()) throw new ResourceNotFoundException("No Users Available!");
        return users;
    }

    @Override
    public void deleteById(long id) {
        SqlParameterSource namedParameters= new MapSqlParameterSource ("id", id);
//        if (namedParameters.)throw new ResourceNotFoundException("No employees available");
        namedParameterJdbcOperations.update(DELETE_USER_BY_ID_QUERY,namedParameters);
    }
}
