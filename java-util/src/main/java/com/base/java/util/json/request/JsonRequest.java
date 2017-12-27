package com.base.java.util.json.request;

import com.base.java.util.json.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class JsonRequest extends JsonBasicHeader {
    private Map<String, String> context;
    private String iface;
    private String method;
    private Map<String, String> args;
    private Map<String, String> metas;

    public JsonRequest() {

    }

    public Map<String, String> genArgs(Method method, Object[] args) {
        if(args == null) return null;
        Map<String, String> argsJson = Maps.newHashMap();
        Parameter[] parameters = method.getParameters();
        for(int index = 0; index < args.length; index++) {
            try {
                argsJson.put(parameters[index].getName(), JsonHelper.getMapper().writeValueAsString(args[index]));
            } catch (JsonProcessingException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
        return argsJson;
    }
}
