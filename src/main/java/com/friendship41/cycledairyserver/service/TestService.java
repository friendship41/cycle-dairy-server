package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.CycleMember;
import com.friendship41.cycledairyserver.data.CycleMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
  @Autowired
  private CycleMemberRepository cycleMemberRepository;

  public CycleMember testMember() {
    return cycleMemberRepository.findById("test").get();
  }
}
