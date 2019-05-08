package com.zhilian.rf_qims.api;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
//import org.apache.commons.codec.binary.Base64;

/**
 * 手机端加密算法（和PC端用的Base64包不同）
 * 具体参考这篇博客（http://blog.csdn.net/pbm863521/article/details/54023009）
 * Created by YiFan on 2017-5-24.
 */
public class Pwd
{
    private static final String PASSWORD_CRYPT_KEY = "@A5$d9`Z";
    private static final String SERVER_CRYPT_KEY = "%2&4@1*3";
    private static final byte[] Iv = { 18, 52, 86, 120, -112, -85, -51, -17 };

    public static String decrypt(String message, String key) throws Exception {
        //byte[] bytesrc = Base64.decodeBase64(message);
        byte[] bytesrc = Base64.decode(message, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(Iv);
        cipher.init(2, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    public static byte[] encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(Iv);
        cipher.init(1, secretKey, iv);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    public static String encrypt(String value) {
        String result = "";
        try {
            //result = Base64.encodeBase64String(encrypt(value, "@A5$d9`Z"));
            result = Base64.encodeToString(encrypt(value, "@A5$d9`Z"), Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return result;
    }

    public static String encryptServer(String value) {
        String result = "";
        try {
            //result = Base64.encodeBase64String(encrypt(value, "%2&4@1*3"));
            result = Base64.encodeToString(encrypt(value, "%2&4@1*3"), Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return result;
    }

    public static String decrypt(String value) {
        String result = "";
        try {
            result = decrypt(value, "@A5$d9`Z");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return result;
    }
}
