package com.base.data.access.alyer.netty;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

public class SmoothDownDoorman {
    private final String secertyKey;

    private SmoothDownDoorman(String secertyKey) {
        this.secertyKey = secertyKey;
    }

    private static class LazyHolder {
        private static final SmoothDownDoorman smoothDownDoorman = new SmoothDownDoorman(AthenaEventLoopGroupCenter);
    }

    public static SmoothDownDoorman getInstance() {
        return LazyHolder.smoothDownDoorman;
    }

    public boolean allowSmoothDown(String token) {
        byte[] decodeBytes = Base64.getDecoder().decode(token);
        String decodeString = new String(decodeBytes, StandardCharsets.UTF_8);
        if(decodeString.length() <= 8) {
            return false;
        }
        String dataText = decodeString.substring(decodeString.length() - 8, decodeString.length());
        String password = decodeString.substring(0, decodeString.length() - 8);

    }

    private boolean checkDate(String dataText) {
        LocalDate localDate = LocalDateTime.now();
        String expectData
    }
}
