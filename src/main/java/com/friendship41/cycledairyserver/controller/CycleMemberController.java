package com.friendship41.cycledairyserver.controller;

import com.friendship41.cycledairyserver.common.AES256Util;
import com.friendship41.cycledairyserver.data.database.CycleMember;
import com.friendship41.cycledairyserver.service.CycleMemberService;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")
public class CycleMemberController {
  private final CycleMemberService cycleMemberService;
  private final AES256Util aes256Util;

  @Autowired
  public CycleMemberController(final CycleMemberService cycleMemberService, final AES256Util aes256Util) {
    this.cycleMemberService = cycleMemberService;
    this.aes256Util = aes256Util;
  }

  @PostMapping("/register")
  public CycleMember registerMember(@RequestBody @Valid CycleMember cycleMember) {
    CycleMember registeredmember = cycleMemberService.register(cycleMember);
    registeredmember.setPassword(null);
    return registeredmember;
  }

  @PostMapping("/login")
  public Object login(@RequestBody CycleMember cycleMember, HttpSession session)
      throws GeneralSecurityException, UnsupportedEncodingException {
    CycleMember loginedMember = cycleMemberService.login(cycleMember);
    if (loginedMember == null) {
      return "{\"result\":\"fail\"}";
    }
    session.setAttribute("memberId", cycleMember.getMemberId());
    String code = UUID.randomUUID().toString();
    session.setAttribute("loginCode", code);
    code = aes256Util.encrypt(code);
    return "{\"result\":\"success\",\"loginCode\":\""+code+"\"}";
  }
}