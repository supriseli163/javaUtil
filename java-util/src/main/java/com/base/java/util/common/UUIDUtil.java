package com.base.java.util.common;

import java.time.LocalDateTime;
import java.util.UUID;

public final class UUIDUtil {
    private UUIDUtil() {}

    private static final int LENGTH = 36;
    private static final int LENGTH_VERIFIER = LENGTH / 6;
    private static final int LENGTH_IDENTIFIER = LENGTH - LENGTH_VERIFIER;
    private static final String SECRET_PHRASE = "zoujinxinshidai.";
    private static final int TIME_STAMPE_LENGTH = 6;

    private static String pseudoBase64(String string) {
        return EncodingUtil.encodeBase64(string).replaceAll("[+/=]", "");
    }

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        System.err.println(uuid);
        long longTimemills = System.currentTimeMillis() / 1000;
        System.err.println(longTimemills);
        //long time64 = longTimemills | 0x000000L;
        String time64 = EncodingUtil.compressNumber(longTimemills);


        String identifierRaw = pseudoBase64(uuid.toString());
        System.err.println(String.format("identifierRaw:[%s], length:[%s]",identifierRaw, identifierRaw.length()));
        String identifier = identifierRaw.substring(0, LENGTH_IDENTIFIER);
        String verifierRaw = pseudoBase64(HashUtil.md5(identifier + SECRET_PHRASE));
        String verifier = verifierRaw.substring(0, LENGTH_VERIFIER);
        System.err.println(String.format("verifierRaw:[%s], length:[%s]",verifierRaw, verifierRaw.length()));
        return identifier + verifier + time64;
    }

    public static boolean verify(String uuId) {
        if(uuId == null || uuId.length() != LENGTH + TIME_STAMPE_LENGTH) {
            return false;
        }

        String identifier = uuId.substring(0, LENGTH_IDENTIFIER);
        String verifier = uuId.substring(LENGTH_IDENTIFIER, LENGTH_IDENTIFIER + LENGTH_VERIFIER);
        String verifierCalculateRaw = pseudoBase64(HashUtil.md5(identifier + SECRET_PHRASE));
        String verifierCalculated = verifierCalculateRaw.substring(0, LENGTH_VERIFIER);
        return verifier.equals(verifierCalculated);
    }

    public static boolean isExpired(String uuId, long expireSeconds) {
        if(!verify(uuId)) return false;
        String timeStamp64 = uuId.substring(LENGTH);
        long createdAtTimeStamp = EncodingUtil.unCompressNumber(timeStamp64);
        System.err.println(TimeUtil.toLocalDateTime(createdAtTimeStamp));
        System.err.println(createdAtTimeStamp);
        return TimeUtil.toLocalDateTime(createdAtTimeStamp).plusSeconds(expireSeconds).isBefore(LocalDateTime.now());
    }
}

