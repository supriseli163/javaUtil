package com.base.data.access.alyer.netty.upgrade;

import com.base.data.access.alyer.constant.Constants;
import com.base.data.access.alyer.netty.AthenaUtils;

import java.util.logging.Logger;

public class Downgrader {
    private static final Logger logger = Logger.getLogger(Downgrader.class.getName());
    private long startDyingTime = 0;

    public void triggerowngrade() {
        logger.info(AthenaUtils.getAPPID());
    }

    private boolean isTimeOut(long timeMills) {
        return (timeMills - startDyingTime > Constants.DYING_TIME_LIMIT);
    }

    private void closeListener() {
        logger.info(AthenaUtils.pidStr() + "is closeListener");
        AthenaUtils
    }

    public void die() throws Exception {
        logger.info(AthenaUtils.getAPPID() + "dies!");
        Thread.sleep(2000);
        System.exit(0);
    }
}
