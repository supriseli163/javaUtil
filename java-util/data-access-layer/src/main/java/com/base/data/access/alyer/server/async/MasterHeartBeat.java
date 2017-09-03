package com.base.data.access.alyer.server.async;

import com.base.data.access.alyer.allinone.DBConnectionInfo;
import javafx.beans.property.adapter.JavaBeanObjectProperty;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class MasterHeartBeat implements HeartBeat {
    public static final Logger logger = Logger.getLogger(MasterHeartBeat.class.getName());
    private static long DEFAULT_INTERVAL = 1000;
    private final DBConnectionInfo dbConnInfo;
    private final AtomicReference<J>


    @Override
    public void start() {

    }

    @Override
    public void destory() {

    }
}
