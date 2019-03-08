package com.uusoft.test.mock.mockserver.utils.encrypt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SecretKeyManager {

  private static final Map<String, String> SECRET_KEY_MAP = Collections
      .synchronizedMap(new HashMap<String, String>());
  protected static Locale locale = Locale.getDefault();

  /**
   * 获取属性文件的键值
   */
  public static String getSecretKey(String key) {
    return SECRET_KEY_MAP.get(key).trim();
  }

  public static void putSecreKey(String key, String keyValue) {
    SECRET_KEY_MAP.put(key, keyValue);
  }

}
