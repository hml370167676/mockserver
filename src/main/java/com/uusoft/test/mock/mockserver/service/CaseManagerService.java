package com.uusoft.test.mock.mockserver.service;

import com.uusoft.test.mock.mockserver.common.Result;
import com.uusoft.test.mock.mockserver.model.CaseManager;
import org.springframework.stereotype.Service;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 10:29 AM 3/6/2019
 * @Modified By:
 */

public interface CaseManagerService {

  /**
   *
   * @Description:
   * 添加用例
   * @auther: minglu@toutoujinrong.com
   * @date: 10:54 AM 3/6/2019
   * @param: [caseManager]
   * @return: com.uusoft.test.mock.mockserver.common.Result<java.lang.String>
   *
   */
  Result<String> creatCase(CaseManager caseManager);

  /**
   *
   * @Description:
   * 通过用例名称查找用例
   * @auther: minglu@toutoujinrong.com
   * @date: 10:54 AM 3/6/2019
   * @param: [caseName]
   * @return: com.uusoft.test.mock.mockserver.common.Result<com.uusoft.test.mock.mockserver.model.CaseManager>
   *
   */
  Result<CaseManager> getCaseInfo(String caseName);

}
