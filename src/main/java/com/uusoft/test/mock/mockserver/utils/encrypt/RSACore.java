package com.uusoft.test.mock.mockserver.utils.encrypt;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

/**
 * 类描述： 用一句话描述该类的作用
 *
 * @author 周辉
 * @version V1.0
 * @company:上海投投金融有限责任公司
 * @email zhouhui@66money.com
 * @since 2016年4月19日 上午10:33:00
 */
public abstract class RSACore extends Coderc {

  public static final String KEY_ALGORITHM = "RSA";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";

  private static final int MAX_ENCRYPT_BLOCK = 117; //RSA最大加密明文大小
  private static final int MAX_DECRYPT_BLOCK = 128;//RSA最大解密密文大小

  /**
   * 用私钥对信息生成数字签名
   *
   * @param data 加密数据
   * @param privateKey 私钥
   */
  public static String sign(byte[] data, String privateKey) throws Exception {
    // 解密由base码的私钥
    byte[] keyBytes = decryptBASE64(privateKey);

    // 构造PKCS8EncodedKeySpec对象
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

    // 取私钥匙对象
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

    // 用私钥对信息生成数字签名
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initSign(priKey);
    signature.update(data);

    return encryptBASE64(signature.sign());
  }

  /**
   * 校验数字签名
   *
   * @param data 加密数据
   * @param publicKey 公钥
   * @param sign 数字签名
   * @return 校验成功返回true 失败返回false
   */
  public static boolean verify(byte[] data, String publicKey, String sign)
      throws Exception {

    // 解密由base码的公钥
    byte[] keyBytes = decryptBASE64(publicKey);

    // 构造XncodedKeySpec对象
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

    // 取公钥匙对象
    PublicKey pubKey = keyFactory.generatePublic(keySpec);

    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initVerify(pubKey);
    signature.update(data);

    // 验证签名是否正常
    return signature.verify(decryptBASE64(sign));
  }

  /**
   * 解密<br> 用私钥解密
   */
  public static byte[] decryptByPrivateKey(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, privateK);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段解密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
        cache = cipher
            .doFinal(data, offSet, MAX_DECRYPT_BLOCK);
      } else {
        cache = cipher
            .doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_DECRYPT_BLOCK;
    }
    byte[] decryptedData = out.toByteArray();
    out.close();
    return decryptedData;
  }

  /**
   * 解密<br> 用公钥解密
   */
  public static byte[] decryptByPublicKey(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);

    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);

    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, publicKey);

    return cipher.doFinal(data);
  }

  public static byte[] decryptByPublicKeyNew(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);

    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);

    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段解密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
        cache = cipher
            .doFinal(data, offSet, MAX_DECRYPT_BLOCK);
      } else {
        cache = cipher
            .doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_DECRYPT_BLOCK;
    }
    byte[] decrydData = out.toByteArray();
    out.close();
    return decrydData;
  }

  /**
   * 加密<br> 用公钥加密
   */
  public static byte[] encryptByPublicKey(byte[] data, String key)
      throws Exception {
    // 对公钥解密
    byte[] keyBytes = decryptBASE64(key);

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicK = keyFactory.generatePublic(x509KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, publicK);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段加密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
        cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_ENCRYPT_BLOCK;
    }
    byte[] encryptedData = out.toByteArray();
    out.close();
    return encryptedData;
  }

  /**
   * 加密<br> 用私钥加密
   */
  public static byte[] encryptByPrivateKey(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);

    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);

    return cipher.doFinal(data);
  }

  /**
   * 加密<br> 用私钥加密
   */
  public static byte[] encryptByPrivateKeyNew(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);

    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段加密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
        cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_ENCRYPT_BLOCK;
    }
    byte[] encryData = out.toByteArray();
    out.close();

    return encryData;
  }


  /**
   * 取得私钥
   */
  public static String getPrivateKey(Map<String, Object> keyMap)
      throws Exception {
    Key key = (Key) keyMap.get(PRIVATE_KEY);

    return encryptBASE64(key.getEncoded());
  }

  /**
   * 取得公钥
   */
  public static String getPublicKey(Map<String, Object> keyMap)
      throws Exception {
    Key key = (Key) keyMap.get(PUBLIC_KEY);

    return encryptBASE64(key.getEncoded());
  }

  /**
   * 初始化密钥
   */
  public static Map<String, Object> initKey() throws Exception {
    KeyPairGenerator keyPairGen = KeyPairGenerator
        .getInstance(KEY_ALGORITHM);
    keyPairGen.initialize(1024);

    KeyPair keyPair = keyPairGen.generateKeyPair();

    // 公钥
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    // 私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

    Map<String, Object> keyMap = new HashMap<String, Object>(2);

    keyMap.put(PUBLIC_KEY, publicKey);
    keyMap.put(PRIVATE_KEY, privateKey);
    return keyMap;
  }

  public static void main(String[] args) throws Exception {
    Map<String, Object> keyMap = initKey();

    System.err.println("公钥: \n\r" + getPublicKey(keyMap));
    System.err.println("私钥： \n\r" + getPrivateKey(keyMap));
  }
}

