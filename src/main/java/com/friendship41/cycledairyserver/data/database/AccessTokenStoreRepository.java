package com.friendship41.cycledairyserver.data.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenStoreRepository extends JpaRepository<AccessTokenStore, String> {
  List<AccessTokenStore> findAllByAccessTokenAndMemberId(String accessToken, String memberId);
  void deleteAllByMemberId(String memberId);
}
