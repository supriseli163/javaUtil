package com.base.data.access.alyer.server.pool;

import com.base.data.access.alyer.allinone.DBConnectionInfo;
import io.netty.util.AbstractReferenceCounted;

import java.util.logging.Logger;

public class ServerSessionPool extends AbstractReferenceCounted {
    private static final Logger logger = Logger.getLogger(ServerSessionPool.class.getName());
    private static int initServeressionNum = 1;

    private final DBConnectionInfo dbConnectionInfo;
    private final ServerSessionFactory

    @Override
    protected void deallocate() {

    }
}
