package com.base.java.util.common;

public final class StringUtil {
    private StringUtil() {}

    public static String uppercaseFirst(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static String lowercaseFirst(String string) {
        return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

    public static boolean isUUID(String string) {
        return string != null && string.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1345][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$");
    }

    public static boolean isLowercaseUUID() {
        return false;
    }

    /**
     * check if a charSequence is whiteSpaces, empty ("") or null
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if(cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if(Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
