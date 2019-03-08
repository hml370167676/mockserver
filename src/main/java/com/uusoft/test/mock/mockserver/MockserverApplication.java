package com.uusoft.test.mock.mockserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = {"com.uusoft.test.mock.mockserver.dao"})
public class MockserverApplication {

  public static void main(String[] args) {
    SpringApplication.run(MockserverApplication.class, args);
  }

}
