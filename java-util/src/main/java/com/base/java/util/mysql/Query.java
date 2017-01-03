package com.base.java.util.mysql;

import java.util.Map;

public interface Query {
    public String getHql();
    public void setHql(String hql);

    public int getOffset();
    public void setOffset(int offset);

    public int getLimit();
    public void setLimit(int limit);

    public <T> T getParameter(String name);
    public Map<String, Object> getParmeterMap();
    public void setParameter(String name, Object value);
}
