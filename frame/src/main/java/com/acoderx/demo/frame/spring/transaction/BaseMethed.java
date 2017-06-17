package com.acoderx.demo.frame.spring.transaction;

import com.acoderx.demo.frame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xudi on 2017/6/17.
 */
@Component
public class BaseMethed {
    @Autowired
    private UserDao userDao;

    /*
    * 修改金额为2000
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void methodA() {
        userDao.updateMoneyById(1, 2000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /* 修改金额为2000
    * 方法内部抛出运行时异常
    * */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void methodAWithRunTimeException() {
        userDao.updateMoneyById(1, 2000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
        throw new RuntimeException();
    }

    /*
    * 修改金额为3000
    * */
    @Transactional(propagation = Propagation.MANDATORY)
    public void methodB() {
        userDao.updateMoneyById(1, 3000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /*
    * 修改金额为3000
    * 方法内部抛出运行时异常
    * */
    public void methodBWithRunTimeException() {
        userDao.updateMoneyById(1, 3000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
        throw new RuntimeException();
    }

    /*
    * 修改金额为4000
    * */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void methodC() {
        userDao.updateMoneyById(1, 4000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /*
    * 修改金额为5000
    * */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void methodD() {
        userDao.updateMoneyById(1, 5000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /*
    * 修改金额为6000
    * */
    @Transactional(propagation = Propagation.NEVER)
    public void methodE() {
        userDao.updateMoneyById(1, 6000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /*
    * 修改金额为7000
    * */
    @Transactional(propagation = Propagation.NESTED)
    public void methodF() {
        userDao.updateMoneyById(1, 7000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }
    /*
    * 修改金额为8000
    * 抛出运行时异常
    * */
    @Transactional(propagation = Propagation.NESTED)
    public void methodFWithRunTimeException() {
        userDao.updateMoneyById(1, 8000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
        throw new RuntimeException();
    }

}
