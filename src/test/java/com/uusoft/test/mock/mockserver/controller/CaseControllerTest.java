package com.uusoft.test.mock.mockserver.controller;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uusoft.test.mock.mockserver.MockserverApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Author: minglu@toutoujinrong.com
 * @Description:
 * @Date: Created in 3:06 PM 3/4/2019
 * @Modified By:
 */
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = { CaseController.class})
//@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
//@ComponentScan(basePackages = "com.uusoft.test.mock.mockserver")

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockserverApplication.class)
@AutoConfigureMockMvc
public class CaseControllerTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private MockMvc mvc;

  @BeforeClass
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new CaseController()).build();
  }

  @Test
  public void testController() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/case/test").accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().string(equalTo("")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
