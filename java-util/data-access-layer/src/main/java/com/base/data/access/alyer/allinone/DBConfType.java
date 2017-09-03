package com.base.data.access.alyer.allinone;

import com.google.common.base.Strings;

public enum  DBConfType {
    NONE("#"),  NORMAL(""), SEQUNCE("$"), DBDELAY("@");

    private DBConfType(String symbol) {
        this.symbol = symbol;
    }

    private final String symbol;

    public String getSymbol() {
        return symbol;
    }

    public static DBConfType getType(String line) {
        if(line == null || Strings.isNullOrEmpty(line)) {
            return NONE;
        }
        line = line.trim();
        if(line.startsWith(NONE.symbol)) {
            return NONE;
        }

        if(line.startsWith(SEQUNCE.symbol)) {
            return SEQUNCE;
        }

        if(line.startsWith(DBDELAY.symbol)) {
            return DBDELAY;
        }
        return NORMAL;
    }
}
