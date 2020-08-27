package com.friendship41.cycledairyserver.controller;

import com.friendship41.cycledairyserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
  @Autowired
  private TestService testService;

  @GetMapping("/member")
  public Object memberTest() {
    return testService.testMember();
  }
}
