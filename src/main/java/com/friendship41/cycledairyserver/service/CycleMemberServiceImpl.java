package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.database.CycleMember;
import com.friendship41.cycledairyserver.data.database.CycleMemberRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("CycleMemberService")
public class CycleMemberServiceImpl implements CycleMemberService {
  private final CycleMemberRepository cycleMemberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public CycleMemberServiceImpl(final CycleMemberRepository cycleMemberRepository) {
    this.cycleMemberRepository = cycleMemberRepository;
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public CycleMember register(final CycleMember cycleMember) {
    cycleMember.setRegisterDate(new Date());
    cycleMember.setPassword(bCryptPasswordEncoder.encode(cycleMember.getPassword()));
    return cycleMemberRepository.save(cycleMember);
  }

  @Override
  public CycleMember login(final CycleMember cycleMember) {
    Optional<CycleMember> dbmember = cycleMemberRepository.findById(cycleMember.getMemberId());
    if (!dbmember.isPresent()) {
      return null;
    }

    if (!bCryptPasswordEncoder.matches(cycleMember.getPassword(), dbmember.get().getPassword())) {
      return null;
    }

    dbmember.get().setPassword(null);
    return dbmember.get();
  }
}
