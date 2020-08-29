package com.friendship41.cycledairyserver.config;

import com.friendship41.cycledairyserver.common.AES256Util;
import java.io.IOException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
  private final AES256Util aes256Util;

  @Autowired
  public AuthInterceptor(AES256Util aes256Util) {
    this.aes256Util = aes256Util;
  }

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
      throws Exception {
    String accessToken = request.getHeader("access_token");
    if (accessToken == null) {
      this.responseAccessTokenError(response);
      log.warn("access_token is missing");
      log.info("=====================end=====================");
      return false;
    }
    String originCode;
    try {
      originCode = aes256Util.decrypt(accessToken);
      if (originCode.equals(request.getSession().getAttribute("access_token"))) {
        return true;
      }
    } catch (IllegalBlockSizeException ignored) {
      this.responseAccessTokenError(response);
    }

    log.warn("access_token is not valid");
    log.info("=====================end=====================");
    return false;
  }
  private void responseAccessTokenError(HttpServletResponse response) throws IOException {
    response.getWriter().write("{\"message\":\"tokenError\"}");
  }
}
