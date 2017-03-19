package com.base.java.util.mysql;

public abstract class Dao<T extends Model> {
//    private SessionHolder sessionHolder;
//    private Class<T> modelType;
//
//    public Dao() {
//        sessionHolder = new SessionHolder();
//        Type type = getClass().getGenericSuperclass();
//        Type[] trueTypes = ((ParameterizedType)type).getActualTypeArguments();
//        this.modelType = (Class<T>)trueTypes[0];
//    }
//
//    protected Session getSession() {
//        return sessionHolder.getSession();
//    }
//
//    private org.hibernate.Query toHquery(Query query) {
//        org.hibernate.Query hQuery = getSession().createQuery(query.getHql());
//        for(Map.Entry<String, Object> param : query.getParmeterMap().entrySet()) {
//            if(param.getValue() Collection) {
//                hQuery.setParameterList(param.getKey(), (Collection)param.getValue());
//            } else {
//                hQuery.setParameter(param.getKey(), param.getValue());
//            }
//        }
//
//        if(query.getOffset() > 0) {
//            hQuery.setFirstResult(query.getOffset());
//        }
//        if(query.getLimit() > 0) {
//            hQuery.setMaxResults(query.getLimit());
//        }
//    }
//
//    private String getTablaName() {
//        Table annotation = modelType.getAnnotation(Table.class);
//        return annotation.name();
//    }
//
//    public long count(Query query) {
//        long start = System.currentTimeMillis();
//        try {
//            query.setHql("SELECT COUNT(*) " + query.getHql());
//            return (long)toHquery(query).uniqueResult();
//        } catch (Exception e) {
//            //
//            return 0;
//        }
//    }
//
//    protected T get(Query query) {
//        long start = System.currentTimeMillis();
//        try {
//            return (T)toHquery(query).uniqueResult();
//        } catch (Exception e) {
//         return null;
//        }
//    }
//
//    public T getById(Serializable id) {
//        long start = System.currentTimeMillis();
//        try {
//            return (T)getSession().get(modelType, id);
//        } finally {
//            return null;
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<T> getList(Query query) {
//        long start = System.currentTimeMillis();
//        try {
//            return (List<T>)toHquery(query).list();
//        } finally {
//            return null;
//        }
//    }
//
//    public T create(T item) {
//        long start = System.currentTimeMillis();
//        try {
//            return (List<T>)toHquery()
//        }
//    }
}
