package com.base.java.util.designpattern.objectpool;

public class ObjectPoolTest {
    public static void main(String[] args) {
        ObjectPool objPool = new ObjectPool();

        objPool.createPool();
        Object obj = objPool.getObject();
        objPool.returnObject(obj);
        objPool.closeObjectPool();
    }
}
