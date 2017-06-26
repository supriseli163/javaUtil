package com.base.java.util.framework.thread;

public class CharIterator {
    public static String converseString(String input) {
        if(input == null || input.length() == 0) {
            return null;
        }

        StringBuffer outPut = new StringBuffer();
        for(int index = input.length() -1 ; index >= 0; index --) {
            outPut.append(input.charAt(index));
        }
        return outPut.toString();
    }

    public static long calculate(String input, String outPut) {
        long inputNum = Long.valueOf(input);
        long outputNum = Long.valueOf(outPut);

        return inputNum - outputNum;
    }

    public static void main(String args[]) {
        String strInput = "3258";
        System.out.println(calculate(strInput, converseString(strInput)));
    }

    public static void preCheck(String input) {
        String regex = "/d+";
        if(!input.matches(regex)) {
            throw new IllegalArgumentException("输入的参数不合法");
        }
    }
}
