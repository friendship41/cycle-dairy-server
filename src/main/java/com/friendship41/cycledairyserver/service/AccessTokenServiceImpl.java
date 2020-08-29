package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.common.AES256Util;
import com.friendship41.cycledairyserver.data.database.AccessTokenStore;
import com.friendship41.cycledairyserver.data.database.AccessTokenStoreRepository;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {
  private final AccessTokenStoreRepository accessTokenStoreRepository;
  private final AES256Util aes256Util;

  @Autowired
  public AccessTokenServiceImpl(final AccessTokenStoreRepository accessTokenStoreRepository, final AES256Util aes256Util) {
    this.accessTokenStoreRepository = accessTokenStoreRepository;
    this.aes256Util = aes256Util;
  }


  @Override
  public boolean checkToken(final String accessToken, final String memberId) {
    List<AccessTokenStore> tokenList =
        accessTokenStoreRepository.findAllByAccessTokenAndMemberId(accessToken, memberId);
    return tokenList != null && tokenList.size() != 0;
  }

  @Override
  @Transactional
  public String insertToken(final String memberId) {
    AccessTokenStore accessTokenStore = new AccessTokenStore(UUID.randomUUID().toString(),
        UUID.randomUUID().toString(), memberId);
    this.accessTokenStoreRepository.deleteAllByMemberId(memberId);
    this.accessTokenStoreRepository.save(accessTokenStore);
    try {
      return aes256Util.encrypt(accessTokenStore.getAccessToken()+"!@#"+memberId);
    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
