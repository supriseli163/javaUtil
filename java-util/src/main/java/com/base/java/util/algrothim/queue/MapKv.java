package com.base.java.util.algrothim.queue;

import com.google.common.collect.Maps;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 有一个字符串它的构成是词+空格的组合,如"北京 杭州 杭州 北京",要求输入一个匹配模式
 * (简单的字符串来写),比如: aabb,来判断该字符串是否符合该模式,举个例子:
 *
 * pattern = "abba" str = "北京 杭州 杭州 北京" return true
 * pattern = "aabb" str = "北京 杭州 杭州 北京" return false
 * pattern = "baab" str = "北京 杭州 杭州 北京" return true
 */
public class MapKv {
    public static boolean pattern(String pattern, String str) {
        char[] patternArray = pattern.toCharArray();
        String[] strArray = str.split(" ");

        if(patternArray.length != strArray.length) {
            return false;
        }

        Map<Character, String> kv = Maps.newHashMap();
        int size = strArray.length;

        for(int index = 0; index < size; index++) {
            char chr = patternArray[index];

            if(kv.containsKey(chr)) {
                if(!strArray[index].equals(kv.get(chr))) {
                    return false;
                }
            }

            synchronized (kv) {
                kv.put(chr, strArray[index]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern1 = "abba";  String str1 = "北京 杭州 杭州 北京";
        String pattern2 = "aabb";  String str2 = "北京 杭州 杭州 北京";
        String pattern3 = "baab";  String str3 = "北京 杭州 杭州 北京";


        assertTrue(pattern(pattern1, str1));
        assertFalse(pattern(pattern2, str2));
        assertTrue(pattern(pattern3, str3));
    }

}
