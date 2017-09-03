package com.base.data.access.alyer.util.delpoy;

import sun.swing.FilePane;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class OrgConfig {
    public static final Logger logger = Logger.getLogger(OrgConfig.class.getName());
    private static OrgConfig instance = new OrgConfig();
    private volatile Map<String, List<DALgroup>> dalGroup = new HashMap<>();
    private boolean isWarnLogWrited = false;

    public static OrgConfig getInstance() {
        return instance;
    }

    public OrgConfig() {

    }

    public synchronized void loadConfig(String filePath) {
        File file = new File(filePath);

        if(!file.exists()) {
            if(!isWarnLogWrited) {
                logger.warning(String.format("org config file %s does not exist.", filePath));
                isWarnLogWrited = true;
            }
        }
    }

    private void localChannelServerHandler(boolean isBatchSwitchAllowed) {
        if(isBatchSwitchAllowed && !LocalDateTime.)
    }
}
