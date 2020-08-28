package com.friendship41.cycledairyserver.data.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpResponseBody {
  private String responseCode = RESPONSE_CODE_SUCCESS;
  private String responseMessage = RESPONSE_MESSAGE_SUCCESS;
  private Object data;

  public static HttpResponseBody createSuccessResponseBody(Object data) {
    return HttpResponseBody.builder()
        .responseCode(RESPONSE_CODE_SUCCESS)
        .responseMessage(RESPONSE_MESSAGE_SUCCESS)
        .data(data)
        .build();
  }

  public static HttpResponseBody createDefaultFailBody() {
    return HttpResponseBody.builder()
        .responseCode(RESPONSE_CODE_SERVER_ERROR)
        .responseMessage(RESPONSE_MESSAGE_SERVER_ERROR)
        .build();
  }

  public static HttpResponseBody createBadRequestBody() {
    return HttpResponseBody.builder()
        .responseCode(RESPONSE_CODE_BAD_REQUEST)
        .responseMessage(RESPONSE_MESSAGE_BAD_REQUEST)
        .build();
  }

  //SUCCESS
  public static final String RESPONSE_CODE_SUCCESS = "200";
  public static final String RESPONSE_MESSAGE_SUCCESS = "성공";
  // COMMON_FAIL
  public static final String RESPONSE_CODE_BAD_REQUEST = "400";
  public static final String RESPONSE_MESSAGE_BAD_REQUEST = "잘못된 요청";
  public static final String RESPONSE_CODE_SERVER_ERROR = "500";
  public static final String RESPONSE_MESSAGE_SERVER_ERROR = "미확인 에러";
  public static final String RESPONSE_CODE_NO_RESULT = "501";
  public static final String RESPONSE_MESSAGE_NO_RESULT = "결과 없음";
}
