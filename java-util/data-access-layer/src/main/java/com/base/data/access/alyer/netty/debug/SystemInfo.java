package com.base.data.access.alyer.netty.debug;

import com.base.data.access.alyer.constant.Constants;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.hat.internal.model.ReachableObjects;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SystemInfo {
    private static final Logger logger = Logger.getLogger(SystemInfo.class.getName());

    private static void addDebugInfo(StringBuilder sb, String s) {
        final String le = System.lineSeparator();
        sb.append(s).append(le);
    }

    public static String getInfo() {
        Map<String, Object> root = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        root.put("current time", sdf.format(new Date()));
        root.put("start time", sdf.format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
        root.put("pid", Constants.PID);
        root.put("APPID", ConstantPool.AP);
        root.put("HOSTNAME", Constants.HOSTNAME);
        root.put("DB conn", AthenaC);
        root.put("");
    }

    private static boolean isStandByMaster(DBCon) {

    }
}
