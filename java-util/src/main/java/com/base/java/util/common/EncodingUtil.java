package com.base.java.util.common;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.UUID;

public final class EncodingUtil {
    private EncodingUtil() {}

    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', '+', '/'
    };


    public static String encodeBase64(String string) {
        return BaseEncoding.base64().encode(string.getBytes());
    }

    public static String decodeBase64(String string) {
        return new String(BaseEncoding.base64().decode(string));
    }

    public static String encodeUrlComponent(String string) {
        try {
            return URLEncoder.encode(string, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static String decodeUrlComponent(String string) {
        try {
            return URLDecoder.decode(string, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 把10进制转换为64进制
     *http://blog.csdn.net/lpayit/article/details/47948351
     * @param number
     * @return
     */
    public static String compressNumber(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 把64进制的字符串转化为10进制
     *
     * @param decompStr
     * @return
     */
    public static long unCompressNumber(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            for (int j = 0; j < digits.length; j++) {
                if (decompStr.charAt(i) == digits[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }

    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }

    public static UUID randomUUID() {
        SecureRandom ng = Holder.numberGenerator;

        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
        randomBytes[6]  |= 0x10;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */

        long msb = 0;
        long lsb = 0;
        assert randomBytes.length == 16 : "data must be 16 bytes in length";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);
//        this.mostSigBits = msb;
//        this.leastSigBits = lsb;
        return new UUID(msb, lsb);
    }
}
