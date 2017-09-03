package com.base.data.access.alyer.server.pool;

import com.base.data.access.alyer.allinone.DBConnectionInfo;
import com.base.data.access.alyer.allinone.DBRole;

import java.util.Map;
import java.util.TreeMap;

public class PoolId {
    private String databse = "";
    private String host;
    private int port;
    private String user;
    private DBRole role = DBRole.DUMMY;
    private boolean isAutoCommit;
    private Map<String, String> additionSessionCfg = new TreeMap<>();

    public PoolId(DBConnectionInfo dbConnectionInfo, boolean isAutoCommit) {
        databse = dbConnectionInfo.getDatabse();
        host = dbConnectionInfo.getHost();
        port = dbConnectionInfo.getPort();
        user = dbConnectionInfo.getUser();
        this.isAutoCommit = isAutoCommit;
        this.additionSessionCfg = additionSessionCfg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoolId poolId = (PoolId) o;

        if (port != poolId.port) return false;
        if (isAutoCommit != poolId.isAutoCommit) return false;
        if (databse != null ? !databse.equals(poolId.databse) : poolId.databse != null) return false;
        if (host != null ? !host.equals(poolId.host) : poolId.host != null) return false;
        if (user != null ? !user.equals(poolId.user) : poolId.user != null) return false;
        if (role != poolId.role) return false;
        return additionSessionCfg != null ? additionSessionCfg.equals(poolId.additionSessionCfg) : poolId.additionSessionCfg == null;

    }

    @Override
    public int hashCode() {
        int result = databse != null ? databse.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isAutoCommit ? 1 : 0);
        result = 31 * result + (additionSessionCfg != null ? additionSessionCfg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PoolId{" +
                "databse='" + databse + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", role=" + role +
                ", isAutoCommit=" + isAutoCommit +
                ", additionSessionCfg=" + additionSessionCfg +
                '}';
    }
}
