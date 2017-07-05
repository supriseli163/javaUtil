package com.base.java.util.common;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;

/**
 * 加密解密
 */
public class CrypUtil {
    public static String encryToMD5(String info) {
        byte[] digsta = null;
        try {
            /**得到一个md5的消息摘要*/
            MessageDigest alga = MessageDigest.getInstance("Md5");
            /**添加要进行计算摘要的信息*/
            alga.update(info.getBytes());
            /**得到摘要*/
            digsta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        /**将摘要转为字符串*/
        //String rs =
    }

    public static String encryptToSHA(String info) {
        /**字节数组*/
        byte[] digsta = null;
        try {
            /**得到一个md5的消息摘要*/
           MessageDigest alga = MessageDigest.getInstance("SHA-1");
           alga.update(info.getBytes());
           /**得到摘要*/
           digsta = alga.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        /**将摘要转为字符串*/
        //String rs =
    }

    /**
     * 创建秘钥
     * @param algorithm
     * @return
     */
    public static SecretKey createSecretKey(String algorithm) {
        //声明KeyGenerator对象
        KeyGenerator keyGene;
        /**声明 秘钥对象*/
        SecretKey desKey = null;
        try {
            /**返回生成指定算法的秘密私钥的KeyGenerator对象*/
            keyGene = KeyGenerator.getInstance(algorithm);
            //生成一个秘钥
            desKey = keyGene.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //返回秘钥
        return desKey;
    }

    /**
     * 将二进制转化为16进制字符串
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        String hs = "";
        String tmp = "";
        for(int n = 0; n < bytes.length; n++) {
            tmp = Integer.toHexString(bytes[n] & 0XFF);
            if(tmp.length() == 1) {
                hs = hs + "0" + tmp;
            }
        }
    }

    public byte[] hex2byte(String hex) {
        byte[] ret = new byte[8];
        byte[] tmp = hex.getBytes();
        for(int i= 0; i < 8; i ++) {
            ret[i] = unitBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }


    /**
     * 将两个ASCII字符合成一个字符,如:EF ---> OXEF
     * @param src0  byte
     * @param src1  byte
     * @return      byte
     */
    public static byte unitBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String((new byte[]{src0}))).byteValue();
        _b0 = (byte)(_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte)(_b0 ^ _b1);
        return ret;
    }

    /**
     * 将制定的对象写入指定的文件
     * @param file  指定写入的文件
     * @param i     要写入的对象
     * @return
     */
    public Object getObjectFromFIle(String file, int i) {
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            for(int j = 0; j < i; j++) {
                obj = ois.readObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }

    public void signToInfo(String info, String signFie) {
        /**从文件中读取私钥*/
        PrivateKey myPrikey = (PrivateKey)getObjectFromFIle("mykeys.bat", 1);
        /**从文件中读取公钥*/
        PublicKey myPubKey = (PublicKey)getObjectFromFIle("myKeys.bat", 2);
        try {
            //signature 对象可用来生成和验证数字签名
            Signature signet = Signature.getInstance("DSA");
            /**初始化签署前面的私钥*/
            //signet.update(myPrikey);
            /**签署或验证所有更新字节的签名,返回签名*/
            byte[] signed = signet.sign();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean validateSign(String signfile) {
        return false;
    }

}
