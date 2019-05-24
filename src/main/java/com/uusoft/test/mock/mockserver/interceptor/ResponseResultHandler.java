package com.uusoft.test.mock.mockserver.interceptor;

import com.uusoft.test.mock.mockserver.annotation.ResponseResult;
import com.uusoft.test.mock.mockserver.common.Result;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.OpResult.ErrorResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 2:09 PM 5/21/2019
 * @Modified By:
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

  //标记名称
  public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

  //是否请求 包含了 包装注解 标记，没有就直接返回，不需要重写返回体
  @Override
  public boolean supports(MethodParameter methodParameter,
      Class<? extends HttpMessageConverter<?>> aClass) {
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = sra.getRequest();
    //判断请求 是否有包装标记
    ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
    return responseResultAnn == null ? false : true;
  }

  @Nullable
  @Override
  public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter,
      MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    log.info("进入返回体，重写格式。处理中......");

    if (o instanceof ErrorResult) {
      log.info("返回值异常，作包装处理中......");
      ErrorResult errorResult = (ErrorResult) o;
      return Result.fail(errorResult.)
    }
    return Result.succeed(o);
  }
}
