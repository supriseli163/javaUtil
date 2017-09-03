package com.base.data.access.alyer.netty;

public interface WatchTableColumn {
    public static final String COLUMN_TYPE_INT = "INT";
    public static final String COLUMN_TYPE_SMALL_INT = "SMALL_INT";
    public static final String COLUM_TYPE_MEDIUM_INT = "MEDIUM_IINT";
    public static final String UNKOWN_CLOUMN = "";
    public static final String UNKOWN_TABLES = "";

    public String getTableName();
    public String getColumnName();
    public String getType();
    public long getWatchingThreshold();
}

