package com.base.java.util.mysql;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.hibernate.Session;

import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public abstract class Dao<T extends Model> {
    private SessionHolder sessionHolder;
    private Class<T> modelType;

    public Dao() {
        sessionHolder = new SessionHolder();
        Type type = getClass().getGenericSuperclass();
        Type[] trueTypes = ((ParameterizedType)type).getActualTypeArguments();
        this.modelType = (Class<T>)trueTypes[0];
    }

    protected Session getSession() {
        return sessionHolder.getSession();
    }

    private org.hibernate.Query toHquery(Query query) {
        org.hibernate.Query hQuery = getSession().createQuery(query.getHql());
        for(Map.Entry<String, Object> param : query.getParmeterMap().entrySet()) {
            if(param.getValue() Collection) {
                hQuery.setParameterList(param.getKey(), (Collection)param.getValue());
            } else {
                hQuery.setParameter(param.getKey(), param.getValue());
            }
        }

        if(query.getOffset() > 0) {
            hQuery.setFirstResult(query.getOffset());
        }
        if(query.getLimit() > 0) {
            hQuery.setMaxResults(query.getLimit());
        }
    }

    private String getTablaName() {
        Table annotation = modelType.getAnnotation(Table.class);
        return annotation.name();
    }

    public long count(Query query) {
        long start = System.currentTimeMillis();
        try {
            query.setHql("SELECT COUNT(*) " + query.getHql());
            return (long)toHquery(query).uniqueResult();
        } catch (Exception e) {
            //
            return 0;
        }
    }

    protected T get(Query query) {

    }
}
