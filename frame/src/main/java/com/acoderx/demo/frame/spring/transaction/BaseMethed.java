package com.acoderx.demo.frame.spring.transaction;

import com.acoderx.demo.frame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
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


    /*
    * 为了测试事务的隔离性，提交前会线程睡眠
    * */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void isolationAwithSleep(){
        userDao.updateMoneyById(1, 8000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
        try {
            Thread.sleep(2000);
            System.out.println("事务睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    * 查看
    * */
    @Transactional(readOnly = true,isolation = Isolation.READ_UNCOMMITTED)
    public void isolationShowResult(){
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
    }

    /*
    * 为了测试事务的隔离性，提交前会线程睡眠
    * */
    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public void isolationBwithSleep(){
        User user = userDao.findById(1);
        System.out.println("事务1："+user.getMoney());
        try {
            System.out.println("事务1睡眠");
            Thread.sleep(2000);
            System.out.println("事务1睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user = userDao.findById(1);
        System.out.println("事务1："+user.getMoney());
    }
    /*
    * 更新
    * */
    @Transactional
    public void isolationB(){
        userDao.updateMoneyById(1,10000);
        System.out.println("事务2更新成功");
    }

    /*
    * 为了测试事务的隔离性，提交前会线程睡眠
    * */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void isolationCwithSleep(){
        userDao.updateAllUserMoney(0);
        System.out.println("事务1清空所有金额");
        if(userDao.count()==0){
            System.out.println("没有金额大于0的用户");
            try {
                System.out.println("事务1睡眠");
                //此时多出了count>0的数据
                Thread.sleep(2000);
                userDao.updateAllUserMoney(10000);
                System.out.println("事务1设置金额为10000");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*userDao.clearAllUserMoney();
        System.out.println("事务1清空所有金额");
        try {
            System.out.println("事务1睡眠");
            Thread.sleep(2000);
            System.out.println("事务1睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("金额大于0的用户个数"+userDao.count());
        System.out.println("事务1结束");*/
    }
    /*
    * 增加条数测试幻读
    * */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void isolationC(){
        System.out.println("事务2查询id1的数据");
        System.out.println(userDao.findById(1));
        User user = new User();
        user.setName("测试用户");
        user.setAge(19);
        user.setMoney(20000);
        userDao.insert(user);
        System.out.println("事务2插入成功");
    }

    /*
    * 为了测试事务的隔离性，提交前会线程睡眠
    * */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void isolationDwithSleep(){
        userDao.updateAllUserMoney(0);
        System.out.println("事务1清空所有金额");
        try {
            System.out.println("事务1睡眠");
            Thread.sleep(2000);
            System.out.println("事务1睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("金额大于0的用户个数"+userDao.count());
        System.out.println("事务1结束");
    }

    /**
    * 开启一个事务修改值为100，2秒后抛出异常回滚
    * */
    @Transactional
    public void updateLose1(){
        userDao.updateMoneyById(1,10000);
        System.out.println("事务1设置值为100");
        try {
            System.out.println("事务1睡眠");
            Thread.sleep(2000);
            System.out.println("事务1睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("事务1结束");
    }

    @Transactional
    public void updateLose2() {
        System.out.println("事务2开始");
        System.out.println(userDao.findById(1));
        userDao.updateMoneyById(1, 4000);
        User user = userDao.findById(1);
        System.out.println(user.getMoney());
        System.out.println("事务2结束");
        throw new RuntimeException();
    }
}
