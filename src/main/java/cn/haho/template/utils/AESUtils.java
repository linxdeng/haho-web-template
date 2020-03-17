package cn.haho.template.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @Description:aes加密工具
 * @Auther: LINX.DENG
 * @Date: 2020/3/17 11:23
 */
public class AESUtils {
    private static final Logger log = LoggerFactory.getLogger(AESUtils.class);

    static {
        //break JCE crypto policy limit
        try {
            Class<?> clazz = Class.forName("javax.crypto.JceSecurity");
            Field nameField = clazz.getDeclaredField("isRestricted");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(nameField, nameField.getModifiers() & ~Modifier.FINAL);

            nameField.setAccessible(true);
            nameField.set(null, java.lang.Boolean.FALSE);
        } catch (Exception ex) {
            log.info("修改aes密码长度限制,发生系统异常:", ex);
        }
    }
    /**
     * 对密文进行解密.
     *
     * @param text 需要解密的密文
     * @return 解密得到的明文
     */
    public static String aesDecrypt(String text, String aesKey) {
        byte[] aesKeyBytes = Base64.decodeBase64(aesKey); // decode 秘钥
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKeyBytes, 0, 16)); // 初始化向量
        byte[] plaintextBytes;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 加密模式为CBC
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] textBytes = Base64.decodeBase64(text); // decode加密密文结果
            plaintextBytes = cipher.doFinal(textBytes);
        } catch (Exception exception) {
            log.warn("解密异常, e={}", exception);
            return null;
        }
        return new String(plaintextBytes, Charset.forName("UTF-8")); // 指定UTF-8字符集
    }

    /**
     * 对密文进行加密.
     *
     * @param plaintext 需要解密的密文
     * @return 解密得到的明文
     */
    public static String aesEncrypt(String plaintext, String aesKey) {
        byte[] aesKeyBytes = Base64.decodeBase64(aesKey);
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKeyBytes, 0, 16));
        byte[] encryptedBytes;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] plaintextBytes = plaintext.getBytes();
            encryptedBytes = cipher.doFinal(plaintextBytes);
        } catch (Exception e) {
            log.warn("解密异常, e={}", e);
            return null;
        }
        return Base64.encodeBase64String(encryptedBytes); //转换为64位编码
    }
}
