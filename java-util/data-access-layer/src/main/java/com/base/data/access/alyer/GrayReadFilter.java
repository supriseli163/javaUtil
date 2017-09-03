package com.base.data.access.alyer;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;
public class GrayReadFilter {
    private volatile List<Pattern> patterns = new ArrayList<>();
    public void setPattern(String patternString) {
        if(patternString == null) {
            return;
        }
        List<Pattern> newPatterns = new ArrayList<>();
        String[] plist = patternString.split(";");
        for(String pattern : plist) {
            if(!pattern.isEmpty()) {
                newPatterns.add(Pattern.compile(pattern));
            }
        }
        this.patterns = newPatterns;
    }

    public boolean isGrayReadClient(final String ip) {
        List<Pattern> patterns = this.patterns;
        return patterns.stream().anyMatch(pattern -> {
            return pattern.matcher(ip).matches();
        });
    }
}
