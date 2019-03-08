/**
 *
 */
package com.uusoft.test.mock.mockserver.utils.encrypt;


import com.uusoft.test.mock.mockserver.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述： RSA加密工具类
 *
 * @author 周辉
 * @version V1.0
 * @company:上海投投金融有限责任公司
 * @email zhouhui@66money.com
 * @since 2016年4月19日 上午10:33:00
 */
public class RSAHelper {

  private static final Logger logger = LoggerFactory.getLogger(RSAHelper.class);

  /**
   * 方法描述： 验证签名合法性
   *
   * @param data 数据包
   * @param sign 签名
   * @param key 密钥
   * @param instId 机构标识
   * @return 成功或失败
   */
  public static boolean topVerify(String data, String sign, String key, String instId) {
    boolean result = false;
    try {
      result = RSACore
          .verify(Coderc.decryptBASE64(data), SecretKeyManager.getSecretKey(instId + key), sign);
    } catch (Exception e) {
      logger.error("校验签名异常：", e);
    }
    return result;
  }

  /**
   * 方法描述： 解密数据包
   *
   * @param data 数据包
   * @param key 密钥
   * @param charset 字符集
   * @param instId 机构标识
   * @return 数据明文
   */
  public static String topDecrypt(String data, String key, String charset, String instId) {
    String plainText = null;
    try {
      plainText = new String(RSACore.decryptByPrivateKey(Coderc.decryptBASE64(data),
          SecretKeyManager.getSecretKey(instId + key)), charset);
    } catch (Exception e) {
      logger.error("解密数据包异常：", e);
    }
    return plainText;
  }

  /**
   * 方法描述： 加密数据包
   *
   * @param data 数据包
   * @param key 密钥
   * @param charset 字符集
   * @param instId 机构标识
   * @return 数据密文
   */
  public static String topEncrypt(String data, String key, String charset, String instId) {
    if (StringUtil.IsEmpty(data)) {
      return data;
    }
    String cipherText = null;
    try {
      cipherText = Coderc.encryptBASE64(RSACore.encryptByPublicKey(data.getBytes(charset),
          SecretKeyManager.getSecretKey(instId + key)));
    } catch (Exception e) {
      logger.error("加密数据包异常：", e);
    }
    return cipherText;
  }

  /**
   * 方法描述： 签名
   *
   * @param data 数据包
   * @param key 密钥
   * @param instId 机构标识
   * @return 签名
   */
  public static String topSign(String data, String key, String instId) {
    if (StringUtil.IsEmpty(data)) {
      return data;
    }
    String signValue = "";
    try {
      signValue = RSACore
          .sign(Coderc.decryptBASE64(data), SecretKeyManager.getSecretKey(instId + key));
    } catch (Exception e) {
      logger.error("签名异常：", e);
    }
    return signValue;
  }
}
