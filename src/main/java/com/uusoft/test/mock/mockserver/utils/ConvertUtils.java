package com.uusoft.test.mock.mockserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 2:58 PM 2/20/2019
 * @Modified By:
 */
public class ConvertUtils {


  /**
   * @Description: Map转String
   * @auther: minglu@toutoujinrong.com
   * @date: 4:01 PM 2/20/2019
   * @param: [data]
   * @return: java.lang.String
   */
  public String maptostring(Map<String, Object> data) {

    return JSON.toJSONString(data);
  }

  /**
   *
   * @Description:
   * String 转 Json
   * @auther: minglu@toutoujinrong.com
   * @date: 4:04 PM 2/20/2019
   * @param: [data]
   * @return: com.alibaba.fastjson.JSON
   *
   */
  public JSON stringtojson(String data) {
    return JSONObject.parseObject(data);
  }
}
