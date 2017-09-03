package com.base.data.access.alyer.server.pool;

import com.base.data.access.alyer.allinone.DBConnectionInfo;

import java.util.logging.Logger;

public class ServerSession {
    private static final Logger logger = Logger.getLogger(ServerSession.class.getName());

    private final long birthday = System.currentTimeMillis();
    protected volatile long onnectionId = 0;
    protected final DBConnectionInfo dbConnectionInfo;
    protected final ServerSession
}
