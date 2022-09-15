package com.github.weaksloth.dolphins.support.http.handler;

import com.github.weaksloth.dolphins.support.http.HttpRestResult;
import com.github.weaksloth.dolphins.support.http.response.HttpClientResponse;
import com.github.weaksloth.dolphins.util.JSONUtils;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import org.apache.http.HttpStatus;

public class ResponseHandler<T> {

  private Type responseType;

  public final void setResponseType(Type responseType) {
    this.responseType = responseType;
  }

  public final HttpRestResult<T> handle(HttpClientResponse response) throws Exception {
    if (HttpStatus.SC_OK != response.getStatusCode()) {
      return handleError(response);
    }
    return convertResult(response, this.responseType);
  }

  private HttpRestResult<T> handleError(HttpClientResponse response) throws Exception {
    String message =
        CharStreams.toString(new InputStreamReader(response.getBody(), Charsets.UTF_8));
    return new HttpRestResult<>(response.getStatusCode(), message, null, false, true);
  }

  public HttpRestResult<T> convertResult(HttpClientResponse response, Type responseType)
      throws Exception {
    InputStream body = response.getBody();
    T extractBody = JSONUtils.parseObject(body, responseType);
    HttpRestResult<T> httpRestResult = (HttpRestResult<T>) extractBody;
    return httpRestResult;
  }
}
