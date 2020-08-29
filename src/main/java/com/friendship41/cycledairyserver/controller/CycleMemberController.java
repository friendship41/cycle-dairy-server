package com.friendship41.cycledairyserver.controller;

import com.friendship41.cycledairyserver.data.database.CycleMember;
import com.friendship41.cycledairyserver.service.AccessTokenService;
import com.friendship41.cycledairyserver.service.CycleMemberService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")
public class CycleMemberController {
  private final CycleMemberService cycleMemberService;
  private final AccessTokenService accessTokenService;

  @Autowired
  public CycleMemberController(final CycleMemberService cycleMemberService, AccessTokenService accessTokenService) {
    this.cycleMemberService = cycleMemberService;
    this.accessTokenService = accessTokenService;
  }

  @PostMapping("/register")
  public CycleMember registerMember(@RequestBody @Valid CycleMember cycleMember) {
    CycleMember registeredmember = cycleMemberService.register(cycleMember);
    registeredmember.setPassword(null);
    return registeredmember;
  }

  @PostMapping("/login")
  public Object login(@RequestBody CycleMember cycleMember) {
    CycleMember loginedMember = cycleMemberService.login(cycleMember);
    if (loginedMember == null) {
      return "{\"result\":\"500\"}";
    }
    String accessToken = accessTokenService.insertToken(loginedMember.getMemberId());
    if (accessToken == null) {
      return "{\"result\":\"400\", \"message\":\"tokenError\"}";
    }
    return "{\"result\":\"200\",\"access_token\":\""+accessToken+"\"}";
  }

  @GetMapping("/checkToken")
  public Object checkToken() {
    return "{\"result\":\"200\", \"message\":\"success\"}";
  }
}
