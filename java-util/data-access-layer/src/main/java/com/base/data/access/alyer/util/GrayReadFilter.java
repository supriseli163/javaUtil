package com.base.data.access.alyer.util;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;
public class GrayReadFilter {
    private volatile List<Pattern> patterns = new ArrayList<>();

    public void setPatterns(String patternString) {
        if(patternString == null) {
            return;
        }

        List<Pattern> newPattern = new ArrayList<>();
        String[] plist = patternString.split("");

        for(String pattern : plist) {
            if(!pattern.isEmpty()) {
                newPattern.add(Pattern.compile(pattern));
            }
        }
        this.patterns = patterns;
    }

    public boolean isGrayReadClient() {

    }
}
