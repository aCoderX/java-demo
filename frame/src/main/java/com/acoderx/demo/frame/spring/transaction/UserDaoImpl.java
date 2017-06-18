package com.acoderx.demo.frame.spring.transaction;

import com.acoderx.demo.frame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by xudi on 2017/6/17.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(Integer id) {
        User u =  jdbcTemplate.queryForObject("select * from user where id = " + id, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setMoney(resultSet.getLong("money"));
            return user;
        });
        //或者
        /*u = jdbcTemplate.queryForObject("select * from user where id = " + id, new BeanPropertyRowMapper<>(User.class));*/
        return u;
    }

    @Override
    public void updateMoneyById(Integer id,long money) {
        jdbcTemplate.update("UPDATE `user` SET `money`=? WHERE `id`=?", p -> {
            p.setLong(1,money);
            p.setInt(2,id);
        });
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from `user` where `money`>0", Integer.class);
    }

    @Override
    public void insert(User user) {
        jdbcTemplate.update("insert into user(`name`,`age`,`money`) values(?,?,?)", p -> {
            p.setString(1,user.getName());
            p.setInt(2,user.getAge());
            p.setLong(3,user.getMoney());
        });
    }

    @Override
    public void updateAllUserMoney(long money) {
        jdbcTemplate.update("UPDATE `user` SET `money`= "+money);
    }
}
