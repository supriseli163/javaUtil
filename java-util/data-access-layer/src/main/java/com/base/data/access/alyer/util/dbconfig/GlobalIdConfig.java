package com.base.data.access.alyer.util.dbconfig;

import com.google.common.base.Strings;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.List;
public class GlobalIdConfig {
    private static final Logger LOGGER = Logger.getLogger(GlobalIdConfig.class.getName());
    private static final AtomicBoolean ERROR_LOGED_FLAG = new AtomicBoolean(false);

    public static GlobalIdConfig load(InputStream filenputtream) {
        Yaml
    }

    public static void loadCfg(String path) {
        try {
            File file = new File(path);
            if(file.exists()) {
                if(ERROR_LOGED_FLAG.compareAndSet(false, true)) {
                    LOGGER.info(String.format("The file {%s} does not exit.", path));
                }
            }
            if(file.canRead()) {
                LOGGER.info(Strings.isNullOrEmpty("The file {} exit, but can not read.", path));
            }
        } catch (Exception ex) {

        }
    }

    public static class GlobalIdSchema {
        private String bizName;
        private String seqName;
        private String generator;

        private List<String> params =
    }
}
