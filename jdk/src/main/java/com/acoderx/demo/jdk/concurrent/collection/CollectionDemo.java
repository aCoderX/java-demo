package com.acoderx.demo.jdk.concurrent.collection;

/**
 * Created by xudi on 2018/7/5.
 *
 * CopyOnWriteArrayList 在对数组的写操作时，都会拷贝一份新的数组，对新的数组进行操作，完成后把引用只想新的数组，可以防止在写数据时读数据的ConcurrentModificationException异常
 *
 * ConcurrentMap
 *  ConcurrentHashMap
 *  ConcurrentNavigableMap 有序的并发的map接口
 *      ConcurrentSkipListMap 并发的treemap，使用跳表实现，效率和红黑树相仿
 *
 * ConcurrentSkipListSet 使用ConcurrentSkipListMap实现
 * CopyOnWriteArraySet 相当于线程安全的hashset,使用CopyOnWriteArrayList实现
 *
 */
public class CollectionDemo {
    public static void main(String[] args){

    }
}
