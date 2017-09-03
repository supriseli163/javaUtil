package com.base.data.access.alyer.netty;

import com.base.data.access.alyer.constant.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Logger;

public class AthenaUtils {
    private static Logger logger = Logger.getLogger(AthenaUtils.class.getName());
    public static String extrnalCommand(String[] ars) {
        String result = null;
        try {
            Process p = Runtime.getRuntime().exec(ars);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            result = br.readLine();
            p.destroy();
        } catch (IOException e) {
            logger.info("Fail to execute xtrnal command:" + String.join(" ", ars) +  e.getMessage());
        }
        return result;
    }

    public static int getAPPID() {
        String appIdStr = extrnalCommand(
                new String[] {"perl", "-e", "print getppid(). \"\n\";"}
        );

        if(appIdStr == null) {
            return 0;
        }

        return Integer.parseInt(appIdStr);
    }

    public static String pidStr() {
        return "Athena (Pid: " + Constants.PID + ")";
    }

    public static long getileCreationTime(Path p) throws IOException {
        BasicFileAttributes attributes;
        attributes = Files.readAttributes(p, BasicFileAttributes.class.getName());
        return attributes.creationTime().toMillis();
    }

    public static String FpidStr() {
        return "Athena (Pid:" + Constants.PID;
    }

    public static long getFileCreatoinTime(Path p) throws IOException {
        BasicFileAttributes attributes;
        attributes = Files.readAttributes(p, BasicFileAttributes.class);

    }

    public static void configLogbackToOldLog() {
        ConfigPr
    }

}
