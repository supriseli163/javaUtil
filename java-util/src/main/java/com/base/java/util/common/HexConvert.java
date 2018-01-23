package com.base.java.util.common;

import java.util.Stack;

public final class HexConvert {
    private HexConvert() {}

    /**64进制字符串*/
    final static char[] digitsOf64 = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z',  '+', '/'
    };

    final static String stringOf64 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";

    /**
     * 把10进制转换为64进制
     * http://blog.csdn.net/lpayit/article/details/47948351
     * @param number
     * @return
     */
    public static String compressNumber(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digitsOf64[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 把64进制的字符串转化为10进制
     * @param decompStr
     * @return
     */
    public static long unCompressNumber(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            for (int j = 0; j < digitsOf64.length; j++) {
                if (decompStr.charAt(i) == digitsOf64[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }

    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(digitsOf64[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();
    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = stringOf64.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }
}
