package com.base.data.access.alyer.allinone;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Data
public class DBConnectionInfo {
    private static final Logger logger = Logger.getLogger(DBConnectionInfo.class.getName());

    private String group = "";
    private String databse = "";
    private String host = "";
    private int port;
    private String user = "";
    private String pword = "";
    private DBRole role = DBRole.DUMMY;
    private String id = "";
    private Boolean alive = true;
    private Boolean readOnly = true;
    private Boolean allowAutoCommit = false;
    private Map<String, String> additionalSessionCfg = new TreeMap<>();
    private Map<String, String> additionalalCfg = new TreeMap<>();
    private String qualifiedDbId = "";
    private String attributes = "";

    public static DBConnectionInfo parseDBConnectionString(String dbConnectionCfgString) {
        DBConnectionInfo info = null;
        String trimLine = dbConnectionCfgString.trim();
        if(trimLine.length() == 0 || trimLine.charAt(0) == '#') {
            return info;
        }
        String[] slpitedLine = trimLine.split("\\s+");
        if(slpitedLine.length < 8) {
            logger.info("Failed to parseDBConnectionString:" + dbConnectionCfgString);
            return info;
        }
        info = new DBConnectionInfo();
        info.setGroup(slpitedLine[0]);
        info.setDatabse(slpitedLine[1]);
        info.setHost(slpitedLine[2]);
        info.setPort(Integer.valueOf(slpitedLine[3]));
        info.setRole(DBRole.valueOf(slpitedLine[4].toUpperCase()));
        info.setId(slpitedLine[5]);
        info.setUser(slpitedLine[6]);
        info.setPword(slpitedLine[7]);
        info.setReadOnly(true);

        if(slpitedLine.length <= 8) {
            return info;
        }
        info.attributes = slpitedLine[8];
        Map<String, String> resultMap =

    }
}
