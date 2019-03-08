package com.uusoft.test.mock.mockserver.enums;

import lombok.Getter;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 11:08 AM 3/6/2019
 * @Modified By:
 */
@Getter
public enum ResultEnums {

  /**
   * @Description:
   */
  CASE_REPEAT("0001", "用例已存在"),
  /**
   * @Description:
   */
  CASE_MISS("0002","用例不存在"),
  /**
   * @Description:
   */
  CREAT_FAIL("0003","新增用例失败");

  private String code;
  private String msg;

  ResultEnums(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static String getMsgByCode(String code) {
    ResultEnums[] resultEnums = ResultEnums.values();
    for (ResultEnums i : resultEnums) {
      if (i.getCode().equals(code)) {
        return i.getMsg();
      }
    }
    return "";
  }

  public static void main(String[] args) {
    String code = "0001";
    String code2 = "";
    System.out.println(ResultEnums.getMsgByCode(code));
    System.out.println(ResultEnums.getMsgByCode(code2));
  }
}
