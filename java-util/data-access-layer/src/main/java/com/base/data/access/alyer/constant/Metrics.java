package com.base.data.access.alyer.constant;

public class Metrics {
    /**Duration*/
    private static final String DURATION_PROXY = "";
    private static final String DURATION_CPROXY = "time.proxy.duration";

    private static final String DURATION_SERVER = "time.server.duration";
    private static final String DURATION_COMMIT = "time.commit.duration";

    /**gary*/
    public static final String DURATION_GRAY_PROXY = "gray.time.proxy.duration";
    public static final String DURATION_GRAY_SERVER = "gray.time.server.duration";

    /**sharding*/
    public static final String AFFECT_ROWS_PROXY = "sharding.affect.row.diff";
    public static final String SECOND_COMMIT_FAIL = "sharding.second.commit.failure";

    /**Mapping sharding*/
    public static final String MAPPING_SQL_FFECT_ZERO = "sharding.mapping.sql.affectedero";
    public static final String MAPPING_SQL_FAIL = "sharding.mapping.sql.failure";

    /**TPS, QPS*/
    public static final String TRANNS_TPS = "";
    public static final String TRANS_PS = "";
    public static final String TRANS_CTPS = "";

    public static final String SEMPAHORE_CONSUMED = "semaphore.consumed";

    public static final String SQL_DBERROR = "sql.error";
}
