package com.base.data.access.alyer.dector;

import java.io.File;
import java.util.logging.Logger;

public class DetectorDelegate {
    private static final Logger logger = Logger.getLogger(DetectorDelegate.class.getName());

    private static final String CLASS_NAME = "me.ele.jarch.athena.detector.SlowDetectorInstance";

    //当前正字使用的Dector
    private static volatile Dector activeDetector = DUMMY;
    //
    private static volatile Dector lastLoadector = DUMMY;
    private static boolean isTurnOn = false;

    public static final Dector DUMMY = new Dector() {
        @Override
        public Object statistic() {
            return "Slow Detector is turn off";
        }

        @Override
        public void startDectect() {

        }

        @Override
        public void addSample(long dur) {

        }
    };

    public static void startDectect() {

    }

    public static void addSample(long dur, ) {

    }

    public static Object statics() {

    }

    public synchronized static void loadJar(String jar) {
        long start = System.currentTimeMillis();
        try {
            File file = new File(jar);
            if(!file.exists()) {
                if (activeDetector != DUMMY) {
                    LOGGER.info("{} has been removed, detector is disabled now", jar);
                }
                activeDetector = DUMMY;
                return;
            }
        }
    }
}
