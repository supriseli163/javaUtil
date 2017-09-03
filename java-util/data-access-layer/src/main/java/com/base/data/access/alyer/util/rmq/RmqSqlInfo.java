package com.base.data.access.alyer.util.rmq;

public class RmqSqlInfo {
    private final String originSql;
    private final String sqlId;
    private final String ip;
    private final String dbName;
    private final int port;
    private final String dalGroup;

    public RmqSqlInfo(String originSql, String sqlId, String dbName, String ip, int port, String dalGroup) {
        this.originSql = originSql;
        this.sqlId = sqlId;
        this.dbName = dbName;
        this.ip = ip;
        this.port = port;
        this.dalGroup = dalGroup;
    }

    @Override
    public String toString() {
        return "RmqSqlInfo{" +
                "originSql='" + originSql + '\'' +
                ", sqlId='" + sqlId + '\'' +
                ", ip='" + ip + '\'' +
                ", dbName='" + dbName + '\'' +
                ", port=" + port +
                ", dalGroup='" + dalGroup + '\'' +
                '}';
    }
}
