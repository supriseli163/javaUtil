package com.base.data.access.alyer.constant;

import java.lang.management.ManagementFactory;

public class Constants {
    public static final String APPID = System.getProperty("APPID", "Unkown");
    public static final String HOSTNAME = System.getProperty("HOSTNAME", "Unkown");

    public static final String SHORT_APPID;
    public static final int TYPICAL_SQL_SIZE = 4096;

    static {
        int idx = APPID.lastIndexOf(".");
        if(idx != -1) {
            SHORT_APPID = APPID.substring(idx + 1);
        } else {
            SHORT_APPID = APPID;
        }
    }

    public static final String FULL_HOSTNAME = System.getenv("FULL_HOSTNAME");
    public static final String ENV = System.getenv("MY_ENV") != null ? System.getenv("MY_ENV"): "dev";
    public static final String IDC;
    public static final String DAL_SEQUENCE_USER = "$dal_sequence";
    public static final String FUSE_DB_FLAGs = "fuse_db_flags";
    public static final String FUSE_PASS_RATE = "fuse_pass_rate";
    public static final String FUSE_WINDOW_MILLS = "fuse_window_millis";
    public static final String SMOKE_ORDER_DOUBLE_WRITE = "my_order_double_write";

    public static final String PID;

    static {
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        if(pid != null && pid.indexOf("@") != -1) {
            pid = pid.substring(0, pid.indexOf("@"));
        }
        PID = pid;

        IDC = "dev".equals(ENV) ? "dev" : HOSTNAME.split("-")[0];
    }

    public static final int FIXED_DEBUG_PORT = 8844;
    public static final int MIN_DYNAMIC_DEBUG_PORT = 8845;
    public static final int MAX_DYNAMIC_DEBUG_PORT = 8899;
    public static final int DUMMY_SEMAPHORE_MAX_COUNT = 5000;
    public static final int SEND_CLIENT_MAX_BUFFER = 64 * 1024;
    public static final int MAX_PG_SERVICE_PORT = 7999;
    public static final int MIN_MYSQL_SERVICE_PORT = 9000;
    public static final String MAX_RESULT_BUF_SIZE = "max_result_buf_size";
    public static final String DATABASE_CONNECT_STRING = "database_connect_string";
    public static final String MAX_ACTIVE_DB_SESSIONS = "max_active_db_sessions";
    public static final String MAX_ACTIVE_DB_TRANS = "max_active_db_trans";
    public static final String WORKING_THREADS_COUNT = "working_threads_count";
    public static final String MAX_QUEUE_SIZE = "max_queue_size";
    public static final String REJECT_SQL_BY_PATTERN = "reject_sql_by_pattern";
    public static final String REJECT_SQL_BY_REGULAR_EXP = "reject_sql_by_regularexp";
    public static final String BIND_MASTER_PERIOD = "bind_master_period";
    public static final String MASTER_HEARTBEAT_FASTFAIL_PERIOD = "master_heartbeat_fastfail_period";
    public static final String OVERFLOW_TRACE_FREQUENCY = "overflow_trace_frequency";
    public static final long SMALL_INT_OVERFLOW_TRACE_THRESHHOLD = 16383;
    public static final long MEDIUM_INT_OVERFLOW_TRACE_THRESHHOLD = 4194303;
    public static final long INT_OVERFLOW_TRACE_THRESHHOLD = 1073741823;

    // AutoKiller
    public static final String AK_SLOW_SQL = "autokill_slow_sql";
    public static final String AK_SLOW_COMMIT = "autokill_slow_commit";
    public static final String AK_OPEN = "autokill_open";
    public static final String AK_LOWER_SIZE = "autokill_lower_size";
    public static final String AK_UPPER_SIZE = "autokill_upper_size";

    public static final String HANG_SQL_TIMEOUT = "hang_sql_timeout";

    // Autocommit feature switch
    public static final String ALLOW_AUTOCOMMIT_SWITCH = "allow_autocommit_switch";

    // max qps for group
    public static final String SEMAPHORE_ADAPTER_OPEN = "semaphore_adapter_open";
    public static final String MASTER_MAX_GROUP_QPS = "master_max_group_qps";
    public static final String SLAVE_MAX_GROUP_QPS = "slave_max_group_qps";

    public static final String SEMAPHORE_ADAPTER_WINDOW_SIZE = "semaphore_adapter_window_size";

    // Err Message
    public static final String DAL = "[DAL]";
    public static final String OVER_MAX_RESULT_BUF_SIZE = "Max resultset size.";
    public static final String NOT_SUPPORT_FUNCTION = "Not supported functions: '%s'";
    public static final String MULTIPLE_KEYS_DURING_TRANSACTION = "Multiple sharding keys during transaction";
    public static final String CHAOS_QUERYS_DURING_TRANSACTION = "Mix sharding and non sharding querys during transaction";
    public static final String INVALID_SHARDING_SQL = "Invalid sharding SQL: %s , errorMessage: %s";
    public static final String INVALID_SQL_SYNTAX = "Invalid SQL Syntax: %s , errorMessage: %s";

    // public static final long PROTOCOL_VERSION = 10;
    public static final String SERVER_VERSION = "5.6.21-log";
    // public static final String SERVER_VERSION = "5.6.0-athena-0.0.0";
    public static final long MAX_PACKET_SIZE = (long)1024 * 1024 * 16;

    // other
    public static final String DAL_DEBUG_SERVER_FILE = "/tmp/dal_debug_server_" + Constants.SHORT_APPID + "-" + PID + ".txt";

    public static final int WINDOW_SIZE = 100;
    public static final int MAX_QPS = 20000;
    public static final int DEFAULT_SEMAPHORE = 32;

    // netty autoread 触发大小 10485760 (64K)
    public static final int MAX_AUTOREAD_TRIGGER_SIZE = 64 * 1024;

    // etrace
    public static final String ETRACE_SAMPLE_RATE = "etrace_sample_rate";
    // weak master
    public static final String WEAK_MASTER_SWITCH = "weak_master_switch";

    // transId trace
    public static final String QUERY_PREFIX = "q";
    public static final String TRANS_PREFIX = "t";
    public static final String AUTOCOMMIT_PREFIX = "a";
    public static final String DEFAULT_TRANS_ID = QUERY_PREFIX + 0;

    // userInfo attributes
    public static final String READ_ONLY = "readonly";

    public static final int STARTUP_DELAY_TIME = 20000;

    // smooth upgrade
    public static final String UPGRADE_AUDS_PATH = String.format("\0/data/run/dal_upgrade/%s_upgrade_auds.sock", APPID);
    public static final String DOWNGRADE_AUDS_PATH = String.format("\0/data/run/dal_upgrade/%s_downgrade_auds.sock", APPID);
    public static final int DYING_TIME_LIMIT = 6 * 60 * 1000;

    // dal config field
    public static final String DAL_SEQUENCE_TABLE = "dal_sequence_table";
    public static final String DAL_SEQUENCE_ENV = "dal_sequence_env";
    public static final String DAL_SEQUENCE_CACHE_POLL = "dal_sequence_cache_pool";
    // dal_admin username
    public static final String DAL_ADMIN = "dal_admin";
    // goproxy heartbeat username
    public static final String DAL_HEARTBEAT = "dal_heartbeat";

    public static final String MANUAL_RELEASE_DEFAULT = "yes";
    public static final String MANUAL_RELEASE = System.getProperty("manual_release", MANUAL_RELEASE_DEFAULT).toLowerCase();
    public static final String REUSEPORT_SUPPORT_DEFAULT = "yes";
    public static final String REUSEPORT_SUPPORT = System.getProperty("reuseport_support", REUSEPORT_SUPPORT_DEFAULT).toLowerCase();
    public static final String UPGRADE_FLAG_FILE_PREFIX = APPID + "_athena_smooth_upgrade_flag_";
    public static final long UPGRADE_TIME_LIMIT = (long)5 * 60 * 1000;

    // shadow db
    public static final String DAL_SHADOW_DB = "dal_shadow_db";
    public static final String ALLOW_SHADOWDB = "allow_shadowdb";

    // close service ports delay when in smooth down mode
    public static final long SMOOTH_DOWN_DELAY_IN_MILLS = 10 * 1000;

    // pg db
    public static final String DB_VENDOR = "db_vendor";
    public static final String PG_DB_OVERDUE_IN_MINUTES = "pg_db_overdue_in_minutes";
    public static final String PG_SERVER_VERSION = "9.4.8";

    public static final String BATCH_SWITCH_NAME = "allow_batch_switch";
    public static final String LOCAL_CHANNEL_SERVER_ADDRESS = "athena_local_channel_address";
    public static final String ELE_META_BATCH_ANALYZED_MARKER = "batch";
    public static final String ELE_META_DAL_GROUP = "DALGroup";
    public static final String ELE_META_AUTO_COMMIT_FOR_CLIENT = "autoCommit4Client";
    public static final String ELE_META_BIND_MASTER = "bindM";
    public static final String ELE_META_TRANSID = "transId";
    public static final String ELE_META_SHARDING_INDEX = "shardingidx";
    public static final String ELE_META_SHARD_COL_VAL ="shardColVal";

    // GZS
    public static final String SHOP_ID = "shopid";

    // grey switch
    // zoned order id
    public static final String ZONED_ORDER_ID = "zoned_order_id";
    // globalid partition feature switch
    public static final String GLOBALID_PARTITION_SWITCH = "globalid_partition_switch";
    // whether shardId will embed in order_id
    public static final String SHARDID_EMBEDDED = "shardid_embedded";

    // for detect slow dynamic load jar switch
    public static final String DETECT_SLOW_SWITCH = "detect_slow_switch";

    // whether the sql will be rejected when reshard
    public static final String REJECT_ZERO_SHARDID_SWITCH = "reject_zero_shardid_switch";

    // fake dalgroup for stat gzs client used by dal
    public static final String STAT_GZS_GROUP = "stat_gzs_group";

    public static final int REWIRTE_IN_CONDITION_THRESHOLD = 100;

    // send sql to rmq switch
    public static final String SEND_AUDIT_SQL_TO_RMQ_SWITCH = "send_audit_sql_to_rmq_switch";

    public static final String CREATED_AT = "created_at";

    public static final String REBALANCE_MSG = "SQL is rejected due to resharding";

    public static final String CROSS_EZONE_MSG = "SQL is rejected due to cross ezone";

    public static final String DAL_CONFIG_ERR_PWD = "DAL_CONFIG_ERR_PWD";

    // Login trace phrase
    public static final String CHANNEL_ACTIVE = "channelActive";
    public static final String MYSQL_HANDSHAKE_SENT = "handshakeSent";
    public static final String MYSQL_HANDSHAKE_RESPONSE_RECEIVED = "respRecv";
    public static final String AUTH_OK_SENT = "authOkSent";
    public static final String AUTH_OK_BROKEN = "authOkBroken";
    public static final String AUTH_ERR = "authErr";

    public static final String PG_SSL_RECEIVED = "SSLRecv";
    public static final String PG_STARTUP_RECEIVED = "startupRecv";
    public static final String PG_AUTH_RESPONSE_RECEIVED = "authPwdRecv";
    public static final String PG_NO_SSL_SENT = "noSSLSent";
    public static final String PG_AUTH_METHOD_SENT = "authMethodSent";
}
