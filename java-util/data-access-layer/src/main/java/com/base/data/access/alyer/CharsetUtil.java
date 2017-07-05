package com.base.data.access.alyer;

import java.util.HashMap;
import java.util.Map;

public class CharsetUtil {
    private static final String[] INDEX_TO_CHARSET = new String[99];
    private static final String[] INDEX_TO_CHASET = new String[99];
    private static final Map<String, Integer> CHARSET_TO_INDEX = new HashMap<>();
    private static final Map<String, Integer> DB_CHARSET_TO_INDEX = new HashMap<>();

    public static final String UTF8_MB4 = "utf8m4";
    public static final String UTF8 = "utf8";

}
