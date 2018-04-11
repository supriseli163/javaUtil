package com.util.java.util.basic;

import org.apache.commons.collections.map.IdentityMap;
import org.testng.annotations.Test;

import java.util.*;

public class MapTest {
    /**
     * Map分为4种:
     * {@link java.util.Map}
     * {@link java.util.HashMap}
     * {@link java.util.LinkedHashMap}
     * {@link java.util.TreeMap}
     * {@link java.util.Hashtable}
     */

    /**
     * {@link java.util.Map}
     *      有四个实现类:HashMa, HashTable, LinkedHashMap和TreeMap
     * {@link java.util.Map}
     *      主要存储键值对,根据键得到值,因此不允许键重复(重复了就覆盖,但是值可以重复)
     * {@link java.util.HashMap}
     *      是一个最常用的Map,它根据键的HashCode值存储数据,根据key可以直接获取value,具有很快的访问速度,遍历时,取得数据的顺序是完全随机的
     *      HashMap最多只允许一条记录的key为null, 允许多条记录的value为null,
     *      HashMap不支持线程同步,可以使用{@link java.util.concurrent.ConcurrentHashMap}
     * {@link java.util.Hashtable}
     *      与HashMap类似,继承自Dictionary类,
     *      不同的是,不允许记录的键或值为空,它支持线程同步,即任一时刻只有一个线程能写HashTable,因此也导致了HashTable在写入时会比较慢
     * {@link java.util.LinkedHashMap}
     *      保存了记录的插入顺序,在用Iterator遍历时,先得到的是先插入的记录
     * {@link java.util.TreeMap}
     *      能够把它保存的记录根据键排序,默认是按照升序排序的,也可以指定排序的比较器
     *      当用Iterator遍历Treemap时,得到的记录是排过序的,TreeMap的键和值都不能为空
     *
     *
     * Map遍历:
     *  遍历Map有两种方法:
     *  (1),map的keySet()方法获得键的集合,再调用键集合的Iterator方法获得键的迭代器,
     *      以此迭代地取出Map中key,用get方法获得对应键的值,便完成了map的遍历
     *      Iterator it = map.keySet().iterator();
     *      while(it.hashNext()) {
     *          key = it.next();
     *          value = map.get(key);
     *          System.out.println("key:" + key + "; value:" + value);
     *      }
     *
     */
    @Test
    public void testMap() {
        System.err.println("------HashMap的无序输出-------");
        Map hashMap = new HashMap();
        mapInit(hashMap);

        System.err.println("------HashTable的无序输出-------");
        Map hashTable = new Hashtable<>();
        mapInit(hashTable);

        System.err.println("------LinkedHashMap的按照存储顺序输出");
        Map linkedHashMap = new LinkedHashMap<>();
        mapInit(linkedHashMap);

        System.err.println("------TreeMap的按照key规则输出");
        Map treeMap = new TreeMap<>();
        mapInit(treeMap);
    }

    public static void mapInit(Map map) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("3", "Value3");
        map.put("1", "Value1");
        map.put("2", "Value2");
        map.put("b", "ValueB");
        map.put("a", "ValueA");

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("Key:" + entry.getKey() + "=====Value:" + entry.getValue());
        }
    }

    /**
     * IdentityHashMap对象中添加了判断key-value中的key是最新创建的字符串对象，他们通过"=="
     * 比较不相等，所以IdentityHashMap会把他们当成2个key来处理；
     * 后2个key-value都是自出串直接量，是 primitive类型，而且他们的字符串序列完全相同，java
     * 使用常量池来管理字符串，所以他们通过"=="来比较返回true,identityHashMap会认为他们是同一个key
     * 因此只有一次可以添加成功。
     * 是指
     */
    @Test
    public void testIdentityHashMap() {
        Map<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put(new String("xiaoming"), "12");
        identityHashMap.put(new String("xiaoming"), "13");
        identityHashMap.put("java", "14");
        identityHashMap.put("java", "15");
        System.err.println(identityHashMap);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(new String("xiaoming"), "12");
        hashMap.put(new String("xiaoming"), "13");
        hashMap.put("java", "14");
        hashMap.put("java", "15");
        System.err.println(hashMap);

    }
}