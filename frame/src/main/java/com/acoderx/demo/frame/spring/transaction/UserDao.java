package com.acoderx.demo.frame.spring.transaction;

import com.acoderx.demo.frame.domain.User;

/**
 * Created by xudi on 2017/6/17.
 */
public interface UserDao {
    User findById(Integer id);
    void updateMoneyById(Integer id,long money);
    int count();
    void insert(User user);
    void updateAllUserMoney(long money);
}
