package com.friendship41.cycledairyserver.service;

public interface AccessTokenService {
  boolean checkToken(String accessToken, String memberId);
  String insertToken(String memberId);
}
