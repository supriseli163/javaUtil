package com.base.data.access.alyer.netty;

import java.util.logging.Logger;

public class PreLoad {
    private static final Logger logger = Logger.getLogger(PreLoad.class.getName());

    private static class LazyHolder {
        private static final PreLoad INSTANCE = new PreLoad();
    }

    public static PreLoad getInstance() {
        return LazyHolder.INSTANCE;
    }

    private PreLoad() {

    }

    public boolean load() {
        logger.info("Pre connect zookeeper.....");
    }

    private void initZK() {
    }
}
