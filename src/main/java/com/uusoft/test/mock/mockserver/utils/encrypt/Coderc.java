/**
 * <p>Title: Coder.java</p>
 *
 * @Package com.uusoft.rsa.coder <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author 周辉
 * @version V1.0
 * @since 年6月4日 下午2:
 */
package com.uusoft.test.mock.mockserver.utils.encrypt;

import java.security.MessageDigest;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * 类描述：
 *   用一句话描述该类的作用
 *
 * @author 周辉
 * @company:上海投投金融有限责任公司
 * @email zhouhui@66money.com
 * @since 2016年4月19日 上午10:33:00 
 * @version V1.0
 */
@SuppressWarnings("restriction")
public abstract class Coderc {

  public static final String KEY_SHA = "SHA";
  public static final String KEY_MD5 = "MD5";

  /**
   * MAC算法可选以下多种算法
   *
   * <pre>
   * HmacMD5
   * HmacSHA1
   * HmacSHA
   * HmacSHA
   * HmacSHA
   * </pre>
   */
  public static final String KEY_MAC = "HmacMD5";

  /**
   * BASE密
   *
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] decryptBASE64(String key) throws Exception {
    return (new BASE64Decoder()).decodeBuffer(key);
  }

  /**
   * BASE密
   *
   * @param key
   * @return
   * @throws Exception
   */
  public static String encryptBASE64(byte[] key) throws Exception {
    return new BASE64Encoder().encode(key);
  }

  /**
   * MD5加密
   *
   * @param data
   * @return
   * @throws Exception
   */
  public static byte[] encryptMD5(byte[] data) throws Exception {

    MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
    md5.update(data);

    return md5.digest();

  }

  /**
   * SHA加密
   *
   * @param data
   * @return
   * @throws Exception
   */
  public static byte[] encryptSHA(byte[] data) throws Exception {

    MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
    sha.update(data);

    return sha.digest();

  }

  /**
   * 初始化HMAC密钥
   *
   * @return
   * @throws Exception
   */
  public static String initMacKey() throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

    SecretKey secretKey = keyGenerator.generateKey();
    return encryptBASE64(secretKey.getEncoded());
  }

  /**
   * HMAC加密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

    SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
    Mac mac = Mac.getInstance(secretKey.getAlgorithm());
    mac.init(secretKey);

    return mac.doFinal(data);

  }
}

