package com.friendship41.cycledairyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CycleDairyServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CycleDairyServerApplication.class, args);
  }

}
