package com.uusoft.test.mock.mockserver.utils;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.matches;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p> Description: TODO(用一句话描述该文件做什么) </p> <p> Company:上海投投金融有限责任公司 </p>
 *
 * @author 周辉/zhouhui@66money.com
 * @version V1.0
 * @since 2016年4月19日 下午5:41:22
 */
@SuppressWarnings("restriction")
public class StringUtil {

  public static final String MOBILE_PATTERN = "1[345789]\\d{9}";

  /**
   * 获取字符串中的日期部分，如果不为日期时间格式，则返回原对象
   */
  public static String getDate(String dateTime) {
    return dateTime != null && dateTime.length() >= 10 ? dateTime
        .substring(0, 10) : dateTime;
  }

  public static boolean compare(Object obj1, Object obj2) {
    if (null == obj1 && null == obj2) {
      return true;
    }
    if ((null == obj1 && null != obj2) || (null != obj1 && null == obj2)) {
      return false;
    }
    if (obj1.toString().trim().equals(obj2.toString().trim())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 判断字符串是否为数字，不包括小数点
   */
  public static boolean isNumeric(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern = compile("-?[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 判断字符串是否为Double型数字
   */
  public static boolean isDouble(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern = compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 获取string，如果为null,返回空
   */
  public static String validateConvert(Object obj) {
    return obj != null ? obj.toString() : "";
  }

  public static String getArrayStr(String[] obj) {
    if (null != obj) {
      StringBuffer sb = new StringBuffer();
      for (String str : obj) {
        sb.append(str);
      }
      return sb.toString();
    } else {
      return "";
    }
  }

  /**
   * 获取base64位
   */
  public static String encodeBASE64(String s) {
    if (s == null) {
      return null;
    }
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(s.getBytes());
  }

  /**
   * deBase64
   */
  public static String decodeBASE64(String s) {
    if (s == null) {
      return null;
    }
    BASE64Decoder decoder = new BASE64Decoder();
    try {
      byte[] b = decoder.decodeBuffer(s);
      return new String(b);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 全角转半角
   */
  public static String convertToDBC(String input) {
    input = StringUtil.validateConvert(input);
    char c[] = input.toCharArray();
    for (int i = 0; i < c.length; i++) {
      if (c[i] == '\u3000') {
        c[i] = ' ';
      } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
        c[i] = (char) (c[i] - 65248);

      }
    }
    String returnString = new String(c);

    return returnString;
  }

  /**
   * 将数组组合成SQL In语法
   */
  public static String convertToSqlIn(String[] args) {
    if (null == args || args.length == 0) {
      return "";
    } else {
      StringBuffer sb = new StringBuffer();
      for (String key : args) {
        sb.append("'" + key + "',");
      }
      return sb.toString().substring(0, sb.toString().length() - 1);
    }
  }

  /**
   * 对字符串当中的JS代码 进行过滤,全部替换为""
   *
   * @param str 要过滤的字符串
   * @return 过滤后的字符串
   */
  public static String decodeJSScript(String str) {
    if (str == null || "".equals(str)) {
      return "";
    } else {
      String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
      str = compile(regEx, CASE_INSENSITIVE).matcher(str)
          .replaceAll("");
      return str;
    }
  }

  public static String getLowerDateSqlStr(String str, String con) {
    return "TO_DATE('" + str + " 00:00:00','yyyy-MM-dd HH24:mi:ss')<="
        + con;
  }

  public static String getUperDateSqlStr(String str, String con) {
    return "TO_DATE('" + str + " 23:59:59','yyyy-MM-dd HH24:mi:ss')>="
        + con;
  }

  /**
   * 获取字符串中的日期部分，如果不为日期时间格式，则返回原对象
   *
   * @param dateTime
   * @return
   */
  private static int run = 10;

  private static SimpleDateFormat dateFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss");
  private static SimpleDateFormat sTimeStampFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss");

  public static boolean isValidDate(String s) {
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(s);
      return true;
    } catch (Exception e) {
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      return false;
    }
  }

  public static boolean isValidDate(String s, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(s);
      return true;
    } catch (Exception e) {
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      return false;
    }
  }

  public static boolean isValidYYYYMMDD(String s) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(s);
      return true;
    } catch (Exception e) {
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      return false;
    }
  }

  public String m2(double f) {
    DecimalFormat df = new DecimalFormat("#.00");
    return df.format(f);
  }

    /*
     * public static String getRemortIP(HttpServletRequest request) { if (request.getHeader("x-forwarded-for") == null)
     * { return request.getRemoteAddr(); } return request.getHeader("x-forwarded-for"); }
     */

  public static String getTimeInMillis() {

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String myTime = sdFormat.format(date);
    if (run < 99) {
      run++;
    } else {
      run = 10;
    }
    return myTime + run;
  }

  public static String getNowTimeInMillis() {

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String myTime = sdFormat.format(date);

    return myTime;
  }

  public static String getOrderNO(long productid) {

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    // NumberFormat formatter = NumberFormat.getNumberInstance();
    // formatter.setMinimumIntegerDigits(10);
    // formatter.setGroupingUsed(false);
    // String s = formatter.format(productid);

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String myTime = sdFormat.format(date);
    if (run < 99) {
      run++;
    } else {
      run = 10;
    }
    return myTime + run;
  }

  public static String getOrderNO() {

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    // NumberFormat formatter = NumberFormat.getNumberInstance();
    // formatter.setMinimumIntegerDigits(10);
    // formatter.setGroupingUsed(false);
    // String s = formatter.format(productid);

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String myTime = sdFormat.format(date);
    if (run < 99) {
      run++;
    } else {
      run = 10;
    }
    return myTime + run;
  }

  public static String getTransNo() {

    return getOrderNO();
  }

  public static String getAliPayTransNo() {

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    // NumberFormat formatter = NumberFormat.getNumberInstance();
    // formatter.setMinimumIntegerDigits(10);
    // formatter.setGroupingUsed(false);
    // String s = formatter.format(productid);

    SimpleDateFormat sdFormat = new SimpleDateFormat("yMMddHHmmss");

    String myTime = sdFormat.format(date);
    if (run < 99) {
      run++;
    } else {
      run = 10;
    }
    return myTime + run;
  }

  /**
   * 转换为标准的日期戳
   */
  public static String getTimeStamp() {
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();

    return getTimeStamp(date);
  }

  public static String getTimeStamp(Date date) {
    if (null == date) {
      return "";
    } else {
      return sTimeStampFormat.format(date);
    }
  }

  public static Date StroToDate(String strDate) {
    SimpleDateFormat dformat = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    Date RetDate = null;
    try {
      RetDate = dformat.parse(strDate);
    } catch (Exception e) {
      // TODO: handle exception
    }

    return RetDate;
  }

  public static Date StroToDate(String strDate, String format) {
    SimpleDateFormat dformat = new SimpleDateFormat(
        format);
    Date RetDate = null;
    try {
      RetDate = dformat.parse(strDate);
    } catch (Exception e) {
      // TODO: handle exception
    }

    return RetDate;
  }

  public static String DateToStr(Date date) {
    SimpleDateFormat dformat = new SimpleDateFormat(
        "yyyyMMdd");
    return dformat.format(date);
  }

  public static String DateToStr(Date date, String format) {
    SimpleDateFormat dformat = new SimpleDateFormat(
        format);
    return dformat.format(date);
  }

  /**
   * 判断是否是汉字、字母、数字组成
   */
  public static boolean isChinaOrNumbOrLett(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern = compile("^[0-9a-zA-Z\u4e00-\u9fa5]+$");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 判断是否是手机号码
   */
  public static boolean isPhone(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern = compile("^(13|15|18)\\d{9}$");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 判断是否是邮箱
   */
  public static boolean isEmail(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern =
        compile("^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 验证字符串为Boolean型
   */
  public static boolean isBoolean(String str) {
    return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str) ? true
        : false;
  }

  public static Integer getInteger(Object obj) {
    return StringUtil.isNumeric(StringUtil.validateConvert(obj)) ? new Integer(
        obj.toString()) : null;
  }

  public static Short getShort(Object obj) {
    return StringUtil.isNumeric(StringUtil.validateConvert(obj)) ? new Short(
        obj.toString()) : null;
  }

  public static Long getLong(Object obj) {
    return StringUtil.isNumeric(StringUtil.validateConvert(obj)) ? new Long(
        obj.toString()) : null;
  }

  public static BigDecimal getBigDecimal(Object obj) {
    return StringUtil.isNumeric(StringUtil.validateConvert(obj)) ? BigDecimal
        .valueOf(Long.valueOf(obj.toString())) : null;
  }

  public static Double getDouble(Object obj) {
    return getDouble(obj, null);
  }

  public static Double getDouble(Object obj, Double def) {
    return StringUtil.isDouble(StringUtil.validateConvert(obj)) ? new Double(
        obj.toString()) : def;
  }

  public static Float getFloat(Object obj) {
    return StringUtil.isDouble(StringUtil.validateConvert(obj)) ? new Float(
        obj.toString()) : null;
  }

  public static boolean IsNull(Object obj) {
    return obj != null ? false : true;
  }

  public static boolean IsEmpty(Object obj) {

    return obj == null || obj.toString().trim().length() == 0;
  }

  public static boolean IsEmpty(Object... obj) {
    boolean flag = false;
    for (Object s : obj) {
      flag = IsEmpty(s);
      if (flag) {
        break;
      }
    }
    return flag;
  }


  public static String convertToSearchField(String[] args) {
    if (null == args || args.length == 0) {
      return "";
    } else {
      StringBuffer sb = new StringBuffer();
      for (String key : args) {
        sb.append(key + ",");
      }
      return sb.toString().substring(0, sb.toString().length() - 1);
    }
  }

  public static String getRandomStr(int strlen) {
    if (strlen < 1) {
      ;
    }
    strlen = 6;
    int[] seed = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    StringBuffer buffer = new StringBuffer();
    Random ran = new Random();
    for (int i = 0; i < seed.length; i++) {
      int j = ran.nextInt(seed.length - i);
      if (i < strlen) {
        buffer.append(seed[j]);
      } else {
        break;
      }
      seed[j] = seed[seed.length - 1 - i];
    }
    return buffer.toString();

  }

  public static String getRandomPassWord() {

    int[] seed = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    StringBuffer buffer = new StringBuffer();
    Random ran = new Random();
    for (int i = 0; i < seed.length; i++) {
      int j = ran.nextInt(seed.length - i);
      if (i < 6) {
        buffer.append(seed[j]);
      } else {
        break;
      }
      seed[j] = seed[seed.length - 1 - i];
    }
    return buffer.toString();

  }

  /**
   * 正则表达式:(^(13|15|18)\\d{9}$)|(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4 })?$)
   */
  public static boolean isPhoneOrHomeTel(String str) {
    if (str == null || "".equals(str.trim())) {
      return false;
    }
    Pattern pattern =
        compile("(^(13|15|18)\\d{9}$)|(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }


  /**
   * 方法描述： 校验合法性
   */
  public static boolean isLawfulMobile(String mobile) {
    return matches(MOBILE_PATTERN, mobile);
  }

  /**
   * 支持操作种类
   */
  public static StringBuilder fundOperability(String resStatus) {
    StringBuilder fundOperability = new StringBuilder();
    String rg = "0,2,4,5,6,9,a";
    String sg = "1,3,4,5,9,a";
//      String sh="1,2,3,4,6,9,a";
    if (rg.indexOf(resStatus) < 0) {
      if ("".equals(fundOperability.toString())) {
        fundOperability.append("20");
      } else {
        fundOperability.append(",20");
      }
    }
    if (sg.indexOf(resStatus) < 0) {
      if ("".equals(fundOperability.toString())) {
        fundOperability.append("22");
      } else {
        fundOperability.append(",22");
      }
    }
    return fundOperability;
  }
}
