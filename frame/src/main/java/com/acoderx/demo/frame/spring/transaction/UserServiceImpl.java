package com.acoderx.demo.frame.spring.transaction;

import com.acoderx.demo.frame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xudi on 2017/6/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BaseMethed baseMethed;

    /*
    * 当抛出运行时异常，整个方法都会回滚
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagation_REQUIRED() {
        baseMethed.methodA();
        baseMethed.methodBWithRunTimeException();
    }

    /*
    * 将不会回滚
    * */
    @Override
    public void testPropagation_SUPPORTS() {
        baseMethed.methodAWithRunTimeException();
    }

    /*
    * 将抛出异常
    * */
    @Override
    public void testPropagation_MANDATORY() {
        baseMethed.methodB();
    }

    /*
    * methodC没有回滚，金额变为4000
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagation_REQUIRENEW() {
        baseMethed.methodC();
        throw new RuntimeException();
    }

    /*
    * methodD没有回滚，金额变为5000
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagation_NOTSUPPORTED() {
        baseMethed.methodD();
        throw new RuntimeException();
    }

    /*
    * 抛出异常
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagation_NEVER() {
        baseMethed.methodE();
    }

    /*
    * 1.外部事务回滚，被嵌套事务也回滚
    * 2.被嵌套的事务抛出异常，外部事务不影响
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagation_NESTED() {
        baseMethed.methodF();
        baseMethed.methodAWithRunTimeException();
        /*baseMethed.methodA();
        try {
            baseMethed.methodFWithRunTimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /*
    * 脏读：isolationShowResult读取到未提交事务的数据库变化
    * */
    @Override
    public void testIsolation_READUNCOMMITED() {
        try {
            Thread t = new Thread(()-> baseMethed.isolationAwithSleep());
            t.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMethed.isolationShowResult();
    }

    /*
    * 重复读：isolationBwithSleep读取的两次结果不一样
    * */
    @Override
    public void testIsolation_READCOMMITED() {
        try {
            Thread t = new Thread(()-> baseMethed.isolationBwithSleep());
            t.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMethed.isolationB();
    }

    /*
    * 幻读：isolationCwithSleep成功进入判断设置金额
    * */
    @Override
    public void testIsolation_REPEATABLEREAD() {
        try {
            Thread t = new Thread(()-> baseMethed.isolationCwithSleep());
            t.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMethed.isolationC();
    }

    @Override
    public void testIsolation_SERIALIZABLE() {
        try {
            Thread t = new Thread(()-> baseMethed.isolationDwithSleep());
            t.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMethed.isolationC();
    }

    @Override
    public void showResult() {
        User user = userDao.findById(1);
        System.out.println(user);
    }
}
