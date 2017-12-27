package com.base.java.util.common;

import java.util.UUID;

public final class UUIDUtil {
    private UUIDUtil() {}

    private static final int LENGTH = 36;
    private static final int LENGTH_VERIFIER = LENGTH / 6;
    private static final int LENGTH_IDENTIFIER = LENGTH - LENGTH_VERIFIER;
    private static final String SECRET_PHRASE = "zoujinxinshidai.";

    private static String pseudoBase64(String string) {
        return EncodingUtil.encodeBase64(string).replaceAll("[+/=]", "");
    }

    public static String generate() {
        String identifierRaw = pseudoBase64(UUID.randomUUID().toString());
        String identifier = identifierRaw.substring(0, LENGTH_IDENTIFIER);
        String verifierRaw = pseudoBase64(HashUtil.md5(identifier + SECRET_PHRASE));
        String verifier = verifierRaw.substring(0, LENGTH_VERIFIER);
        return identifier + verifier;
    }

    public static boolean verify(String uuId) {
        if(uuId == null || uuId.length() != LENGTH) {
            return false;
        }

        String identifier = uuId.substring(0, LENGTH_IDENTIFIER);
        String verifier = uuId.substring(LENGTH_IDENTIFIER);
        String verifierCalculateRaw = pseudoBase64(HashUtil.md5(identifier + SECRET_PHRASE));
        String verifierCalculated = verifierCalculateRaw.substring(0, LENGTH_VERIFIER);
        return verifier.equals(verifierCalculated);
    }

}

