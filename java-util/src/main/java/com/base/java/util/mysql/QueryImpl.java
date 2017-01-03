package com.base.java.util.mysql;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class QueryImpl implements Query {
    @Getter
    private String hql;
    @Getter
    private int limit;
    @Getter
    private int offset;

    private Map<String, Object> parameterMap = new HashMap<>();

    @Override
    public <T> T getParameter(String name) {
        return null;
    }

    @Override
    public Map<String, Object> getParmeterMap() {
        return null;
    }

    @Override
    public void setParameter(String name, Object value) {

    }
}
