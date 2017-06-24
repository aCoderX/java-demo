package com.acoderx.demo.frame.spring.transaction;

/**
 * Created by xudi on 2017/6/17.
 */
public interface UserService {
    //事务的传播，事务的传播是发生在不同类的方法之中，如果是同一个类的方法，则会当做一个方法来处理不会发生事务传播
    /*
    * 当前方法必须在事务中运行，如果当前事务存在，则在当前事务中运行，否则创建新事务运行
    * */
    void testPropagation_REQUIRED();
    /*
    * 表示当前方法不需要事务上下文，但是如果当前存在事务，则在当前事务中运行
    * */
    void testPropagation_SUPPORTS();
    /*
    * mandatory：强制的
    * 表示当前方法必须在事务中运行，如果当前事务存在，在当前事务中运行，如果不存在，则抛出异常
    * */
    void testPropagation_MANDATORY();
    /*
    * 当前方法必须运行在自己的事务中，如果当前有事务在运行，则当前事务挂起，该方法运行在新创建的事务中
    * */
    void testPropagation_REQUIRENEW();
    /*
    * 表示该方法不运行在事务中，如果当前有事务在运行，则在该方法运行时，事务会被挂起
    * */
    void testPropagation_NOTSUPPORTED();
    /*
    * 表示该方法不运行于事务，如果当前有事务在运行，则抛出异常
    * */
    void testPropagation_NEVER();
    /*
    *表示如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务进行单独地提交或回滚。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样
    * */
    void testPropagation_NESTED();


    //事务的隔离级别
    /*
    * 最低级别，会发生脏读、重复读、幻读
    * */
    void testIsolation_READUNCOMMITED();

    /*
    * 可防止脏读，但不能防止重复读和幻读 oracle、sqlserver的默认级别
    * */
    void testIsolation_READCOMMITED();

    /*
    * 防止脏读、重复读，但是防止保证幻读 mysql的默认级别，但是由于mysql实现了mvcc，所以也可以防止幻读
    * */
    void testIsolation_REPEATABLEREAD();

    /*
    * 最严格，但性能消耗最大
    * */
    void testIsolation_SERIALIZABLE();

    //事务的只读readonly,会根据情况进行调优，如果在readonly中写数据，会抛出异常

    //事务的超时

    //事务的回滚规则，默认是遇到运行时异常回滚


    void showResult();
}
