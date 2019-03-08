package com.uusoft.test.mock.mockserver.service.impl;

import com.uusoft.test.mock.mockserver.common.Result;
import com.uusoft.test.mock.mockserver.dao.CaseManagerMapper;
import com.uusoft.test.mock.mockserver.enums.ResultEnums;
import com.uusoft.test.mock.mockserver.model.CaseManager;
import com.uusoft.test.mock.mockserver.service.CaseManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 10:53 AM 3/6/2019
 * @Modified By:
 */
@Service
public class CaseManagerServiceImpl implements CaseManagerService {

  private static final Logger log = LoggerFactory.getLogger(CaseManagerServiceImpl.class);

  @Autowired
  private CaseManagerMapper caseManagerMapper;

  @Override
  public Result<String> creatCase(CaseManager caseManager) {

    if (caseManagerMapper.selectByPrimaryKey(caseManager.getCaseName()) != null) {
      return Result.fail(ResultEnums.CASE_REPEAT.getCode(), ResultEnums.CASE_REPEAT.getMsg(), null);
    }
    log.info("新增用例信息：{}", caseManager.toString());

    if (caseManagerMapper.insert(caseManager) < 1) {
      log.info("新增用例----{}----失败！", caseManager.getCaseName());
      return Result.fail(ResultEnums.CREAT_FAIL.getCode(), ResultEnums.CREAT_FAIL.getMsg(), null);
    }
    log.info("新增用例----{}----成功！", caseManager.getCaseName());
    return Result.succeed(caseManager.getCaseName());
  }

  @Override
  public Result<CaseManager> getCaseInfo(String caseName) {

    if (caseName == null) {
      return Result.fail(ResultEnums.CASE_MISS.getCode(), ResultEnums.CASE_MISS.getMsg(), null);
    }
    log.info("根据用例名称：----{}----，开始查找用例信息！", caseName);
    CaseManager caseManager = caseManagerMapper.selectByPrimaryKey(caseName);
    if (caseManager == null) {
      log.info("通过用例名称：---{}---未查询到用例信息!", caseName);
      return Result.fail(ResultEnums.CASE_MISS.getCode(), ResultEnums.CASE_MISS.getMsg(), null);
    }
    return Result.succeed(caseManager);
  }

}
