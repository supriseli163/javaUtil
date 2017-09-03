package com.base.data.access.alyer.allinone;

import java.util.logging.Logger;

public class HeartBeatCenter {
    private static final Logger logger = Logger.getLogger(HeartBeatCenter.class.getName());
    private static int maxMissedHeartbeat = 2;
    private static int MASTER_HEARTBEAT_WINDOW = 10 * 100;
    private Map<String, HeartB>
}
