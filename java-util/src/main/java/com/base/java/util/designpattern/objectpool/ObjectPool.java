package com.base.java.util.designpattern.objectpool;

import java.util.Enumeration;
import java.util.Vector;

/**
 * http://www.cnblogs.com/rubylouvre/p/3314775.html
 */
public class ObjectPool {
    private int numObjects = 10;
    private int maxObjects = 50;

    private Vector objects = null;

    public ObjectPool() {

    }

    /**
     * 创建一个对象池
     */
    public synchronized void createPool() {
        if (objects != null) {
            return;
        }

        objects = new Vector();

        for (int x = 0; x < numObjects; x++) {
            if ((objects.size() == 0) || this.objects.size() <this.maxObjects) {
                Object obj = new Object();
                objects.addElement(new PoolObject(obj));
            }
        }
    }

    /**
     * 获得对象
     * @return
     */
    public synchronized Object getObject() {
        if (objects == null) {
            return null;
        }

        Object conn = null;
        while (conn == null) {
            wait(250);
            //重新再试,知道获得可用的对象
            conn = getFreeObject();
        }
        //返回获得的可用对象
        return conn;
    }

    /**
     * 从对象池对象objects中返回一个可用的对象,如果当前没有可用的对象,
     * 则创建几个对象,并放入对象池中.
     * 如果创建后,所有的对象都在使用中,则返回null
     */
    private Object getFreeObject()  {
        Object obj = findFreeObject();
        while (obj == null) {
            wait(100);
            findFreeObject();
        }
        return obj;
    }

    /**
     * 遍历对象池中的所有对象,找到这个需要返回的对象
     * @param obj
     */
    public void returnObject(Object obj) {
        if(objects == null) {
            return;
        }

        PoolObject pObj = null;
        Enumeration enumeration = objects.elements();

        while (enumeration.hasMoreElements()) {
            pObj = (PoolObject) enumeration.nextElement();

            if(obj == pObj.getObject()) {
                pObj.setBusy(false);
                break;
            }
        }
    }

    public synchronized void closeObjectPool() {
        //确保对称性h
        if(objects == null) {
            return;
        }

        PoolObject pObj = null;
        Enumeration enumeration = objects.elements();

        while (enumeration.hasMoreElements()) {
            pObj = (PoolObject) enumeration.nextElement();
            //从对象池中删掉它
            objects.removeElement(pObj);

        }

        objects = null;
    }

    private void  wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找对象池中所有对象,查找一个可用的对象
     * 如果没有可用的对象,则返回null
     */
    private Object findFreeObject() {
        Object obj = null;
        PoolObject pObj = null;

        //获取对象池中的所有的对象
        Enumeration enumeration = objects.elements();

        while (enumeration.hasMoreElements()) {
            pObj = (PoolObject) enumeration.nextElement();

            if (!pObj.isBusy()) {
                obj = pObj.getObject();
                pObj.setBusy(true);
            }
        }
        //返回找到的可用对象
        return obj;
    }

    /**
     * 内部使用的用于保存对象池中的对象的类
     * 此类中有两个成员,一个是对象,另一个是指示此对象是否正在使用的标志
     */
    class PoolObject {
        Object objection = null;

        boolean busy = false;

        public PoolObject(Object objection) {
            this.objection = objection;
        }

        public Object getObject() {
            return objection;
        }

        public void setObject(Object objection) {
            this.objection = objection;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }
}
