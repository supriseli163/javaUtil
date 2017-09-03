package com.base.data.access.alyer.netty;

import com.base.data.access.alyer.server.pool.ServerSession;
import com.base.data.access.alyer.sql.ComposableCommand;

import java.net.Authenticator;
import java.nio.channels.Channel;
import java.util.Queue;
import java.util.logging.Logger;

public class SqlSessionContext extends ComposableCommand implements Comparable<SqlSessionContext> {
    private static final Logger logger = Logger.getLogger(SqlSessionContext.class.getName());
    public boolean bind2master;
    private final String clientInfo;
    public volatile boolean isAutoCommit4Client = true;
    public Channel clientChannel;
    public Queue<byte[]> clientPackets;
    public Queue<byte[]> dbServerPackets;

    public long writeToClientCounts = 0L;
    public final Authenticator authenticator;

    public ServerSession

}
