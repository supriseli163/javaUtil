package com.base.data.access.alyer.netty;

import com.base.data.access.alyer.constant.Constants;
import com.base.data.access.alyer.netty.debug.SystemInfo;

import java.io.File;
import java.util.Date;

public class StartUpMisc {
    public static void doPrepareStartUp() {

    }

    private static void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("System is exiting ... on " + new Date());
                System.out.println(SystemInfo.getInfo());
            }
        }));
    }

    private static void deletebugPortFile() {
        File file = new File(Constants.DAL);
        if(file.isFile() && file.exists()) {
            file.delete();
            System.out.println("Success. delete file=" + Constants.DAL_DEBUG_SERVER_FILE);
        }
    }
}
