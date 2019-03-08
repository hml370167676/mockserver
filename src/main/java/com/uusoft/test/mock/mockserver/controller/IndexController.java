package com.uusoft.test.mock.mockserver.controller;

import com.uusoft.test.mock.mockserver.dao.CaseManagerMapper;
import com.uusoft.test.mock.mockserver.utils.ConvertUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;


/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 2:17 PM 2/20/2019
 * @Modified By:
 */
@Controller
public class IndexController {

  @Resource
  private CaseManagerMapper caseManagerMapper;

//  private static final Logger log = LoggerFactory.getLogger(CaseController.class);
  private static ConvertUtils convertUtils = new ConvertUtils();

 /* @RequestMapping("/index")
  @ResponseBody
  public Map<String,String> index() {
    List<CaseManager> list = caseManagerMapper.select();
    Map<String, String> map = new HashMap<String,String>();
    for (CaseManager caseManager : list) {
      map.put( caseManager.getCaseName(),caseManager.getCaseBody());
    }
    return "list";
  }*/

}
