package com.base.java.util.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 * :
 */
public class RSAUtil {
    private static final byte[] PUBLIC_KEY = new byte[] { 48, 92, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5,
            0, 3, 75, 0, 48, 72, 2, 65, 0, -96, -17, -100, 35, 113, 43, 58, -91, 14, 38, -108, 49, 115, -31, -33, -52,
            -114, 20, 75, -38, -66, -15, -88, -53, 31, 72, -77, -14, 20, 25, 82, 68, 117, 109, -107, 116, -123, -22,
            32, 86, -81, -16, -18, 71, -128, 74, 31, 101, -27, 30, -28, 62, -50, 51, 92, 75, 111, -32, -83, -26, 10,
            91, 0, -117, 2, 3, 1, 0, 1 };
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 公钥加密
     * @param data 带加密数据
     * @return 加密数据
     * @throws Exception
     */
    public static byte[] encryptPublicKey(byte[] data) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(PUBLIC_KEY);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal();
    }

    public static byte[] encryptByPublicKey(String data) throws Exception {
        return encryptPublicKey(data.getBytes());
    }

    public static String encryptByStrPublicKey(String data) throws Exception {
        byte[] eData = encryptPublicKey(data.getBytes());
        return objectMapper.writeValueAsString(eData);
    }
}
