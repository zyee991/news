package com.itcuc.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {
    /**
     * MD5方法
     *
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) {
        return DigestUtils.md5Hex(text + key);
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verifyMd5(String text, String key, String md5) {
        return md5(text, key).equalsIgnoreCase(md5);
    }
}
