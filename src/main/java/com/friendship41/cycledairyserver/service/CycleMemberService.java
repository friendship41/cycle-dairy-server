package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.database.CycleMember;

public interface CycleMemberService {
  CycleMember register(CycleMember cycleMember);
  CycleMember login(CycleMember cycleMember);
}
