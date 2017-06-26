package com.base.java.util.framework.thread;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class File {
    public static void main(String[] args) throws IOException {
        String filePath = "/tmp/test.txt";
        List<String> stringList = getFile(filePath);

        List<String> resultList = Lists.newArrayList();
        stringList.forEach(strLine -> {
            String[] lineArray = convertStrToArray(strLine);
            if (lineArray != null) {
                for (String str : lineArray) {
                    if (str.equals("login")) {
                        resultList.add(lineArray[2]);
                    }
                }
            }
        });

        Map<String, Integer> result = count(resultList);
        for(Map.Entry<String, Integer> map : result.entrySet()) {
            System.out.println(map.getValue() + " " + map.getKey());
        }
    }

    /**
     * 读文件
     *
     * @return
     * @throws IOException
     */
    public static List<String> getFile(String filePath) throws IOException {
        java.io.File targetFile = new java.io.File(filePath);
        return Files.readLines(targetFile, Charsets.UTF_8);
    }


    /**
     * 字符分割为数组
     *
     * @param str
     * @return
     */
    public static String[] convertStrToArray(String str){
        String[] strArray = null;
        //拆分字符为" " ,然后把结果交给数组strArray
        strArray = str.split(" ");

        return strArray;
    }

    /**
     * 统计数组中相同元素出现的次数
     *
     * @return
     */
    public static Map<String, Integer> count(List<String> strings) {
        if(strings == null || strings.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String str: strings){
            int i = 1;
            if(map.get(str) != null){
                i = map.get(str) + 1;
            }
            map.put(str, i);
        }
        return map;
    }
}
