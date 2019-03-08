package com.uusoft.test.mock.mockserver.controller;

import com.uusoft.test.mock.mockserver.common.Result;
import com.uusoft.test.mock.mockserver.dao.CaseManagerMapper;
import com.uusoft.test.mock.mockserver.model.CaseManager;
import com.uusoft.test.mock.mockserver.service.CaseManagerService;
import com.uusoft.test.mock.mockserver.service.impl.CaseManagerServiceImpl;
import com.uusoft.test.mock.mockserver.utils.ConvertUtils;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 2:26 PM 2/20/2019
 * @Modified By:
 */
@RestController
@RequestMapping("case")
public class CaseController {


  @Autowired
  private CaseManagerService caseManagerService;

//  private static final Logger log = LoggerFactory.getLogger(CaseController.class);
  private static ConvertUtils convertUtils = new ConvertUtils();

  @GetMapping("/{casename}")
  public String caseMatch(@PathVariable String casename) {
    Result<CaseManager> data = caseManagerService.getCaseInfo(casename);
    return data.getModel().getCaseBody();
  }

  @PostMapping("/add")
  public Result<String> addCase(String casename, Map<String, Object> casebody) {

    return Result.succeed("");
  }
}
