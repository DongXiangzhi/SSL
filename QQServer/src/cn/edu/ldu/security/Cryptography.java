package cn.edu.ldu.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 * Cryptography类，用于实现一些通用的加密解密算法。包括如下功能函数
 * getHash，对字符串明文计算摘要，主要用于密码加密传输和将密码以摘要形式保存于数据库
 * generateNewKey，生成AES对称密钥
 * @author 董相志，版权所有2016--2018，upsunny2008@163.com
 */
public class Cryptography {
    private static final int BUFSIZE=8192;  //缓冲区大小
    /**
     * getHash，消息摘要算法，实现明文加密功能
     * @param plainText ：待加密的明文
     * @param hashType ：算法类型："MD5"、"SHA-1"、"SHA-256"、"SHA-384"、"SHA-512"
     * @return 密文的16进制字符串
     */
    public static String getHash(String plainText,String hashType) {
        try {
            MessageDigest md=MessageDigest.getInstance(hashType);//算法
            byte[] encryptStr=md.digest(plainText.getBytes("UTF-8"));//摘要
            return DatatypeConverter.printHexBinary(encryptStr); //16进制字符串            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return null;
      }
    }
    /**
     * 用AES对称加密算法，生成一个新的密钥
     * @return 生成的密钥
     */
    public static SecretKey generateNewKey() {
        try {
            //密钥生成器
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
            keyGenerator.init(128); //128,192,256
            SecretKey secretKey=keyGenerator.generateKey();//新密钥
            return secretKey;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}//end class
