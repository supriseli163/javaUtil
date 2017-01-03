package com.base.java.util.mysql;

import lombok.Data;

@Data
public class DataBaseConfig {
    private String url;
    private String username;
    private String password;
    private String modelsPackageName;
    private int maxSize;
    private int timeout;
    private String driver;
}
