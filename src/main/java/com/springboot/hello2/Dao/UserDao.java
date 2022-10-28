package com.springboot.hello2.Dao;

import com.springboot.hello2.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int deleteAll(){
        return this.jdbcTemplate.update("DELETE FROM last_db");
    }

    public void add(User user){
        this.jdbcTemplate.update("INSERT INTO last_db(id,name,password) VALUES (?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id){
        String sql = "SELECT * FROM last_db WHERE id = ?";

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

}
