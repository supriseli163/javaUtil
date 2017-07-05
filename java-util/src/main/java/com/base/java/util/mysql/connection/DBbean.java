package com.base.java.util.mysql.connection;

/**
 * 这是外部可以配置的连接池属性
 * 可以允许外部配置,拥有默认值
 *
 */
public class DBbean {
    private String driverName;
    private String url;
    private String username;
    private String password;
    private String poolName;

    private int minConnections = 1;
    private long maxActiveConnections = 100;
    private boolean isCurrentConnection = true;

    private boolean isCheckPool = true;
    /***/
    private long lazyCheck = 1000 * 60 * 60;
    /**检查频率*/
    private long periodCheck = 100 * 60 * 60;

    public DBbean(String driverName, String url, String userName, String password, String poolName) {
        super();
        this.driverName = driverName;
        this.url = url;
        this.password = password;
        this.poolName = poolName;
    }

    public DBbean() {}
}
