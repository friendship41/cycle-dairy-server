package com.friendship41.cycledairyserver.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class AES256Util {
  private final String iv;
  private final Key keySpec;

  private static final String key = "thisIsProjectCycleDairyServerKey";

  public AES256Util() throws UnsupportedEncodingException {
    this.iv = key.substring(0, 16);
    byte[] keyBytes = new byte[16];
    byte[] b = key.getBytes(StandardCharsets.UTF_8);
    int len = b.length;
    if (len > keyBytes.length) {
      len = keyBytes.length;
    }
    System.arraycopy(b, 0, keyBytes, 0, len);

    this.keySpec = new SecretKeySpec(keyBytes, "AES");
  }

  public String encrypt(String str) throws NoSuchAlgorithmException,
      GeneralSecurityException, UnsupportedEncodingException {
    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
    return new String(Base64.encodeBase64(encrypted));
  }

  public String decrypt(String str) throws NoSuchAlgorithmException,
      GeneralSecurityException, UnsupportedEncodingException, IllegalBlockSizeException {
    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] byteStr = Base64.decodeBase64(str.getBytes());
    return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
  }
}
