package com.Forum.Dao;

import com.Forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tomal on 2017-05-13.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(String username, String pass) {
        final String sql = "INSERT INTO user (user_name,user_password) VALUES (?,?)";

        String name = username;
        String password = pass;

        jdbcTemplate.update(sql, new Object[]{name, password});


    }

    public Collection<User> getAllUsers() {
        String sql = "SELECT * FROM user WHERE user_id<>1";

        List<User> users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(User.class));

        return users;
    }

    public void disableUserByName(String user_name) {
        final String sql = "UPDATE user SET user_status=0 WHERE user_name = ?";
        jdbcTemplate.update(sql, user_name);

    }

    public void enableUserByName(String user_name) {
        final String sql = "UPDATE user SET user_status=1 WHERE user_name = ?";
        jdbcTemplate.update(sql, user_name);

    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName();
        return user_name;
    }

    public String getUserById(int id) {
        String sql = "SELECT user_name FROM user WHERE user_id=?";
        List<User> username = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), id);
        List<User> username1 = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), id);

        String name = username.get(0).getUser_name().toString();
        String name1 = username1.get(0).getUser_name().toString();

        return name1;
    }

    public boolean checkIfUserExists(String target_name) {
        try {
            String sql = "SELECT * FROM user WHERE user_name=?";
            Object check = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(User.class), target_name);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;

    }
}
