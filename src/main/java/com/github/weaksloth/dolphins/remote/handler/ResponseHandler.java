package com.github.weaksloth.dolphins.remote.handler;

import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.TypeReferenceHttpResult;
import com.github.weaksloth.dolphins.remote.response.HttpClientResponse;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpStatus;

public class ResponseHandler<T> {

  private Class<T> responseType;

  public final void setResponseType(Class<T> responseType) {
    this.responseType = responseType;
  }

  public final HttpRestResult<T> handle(HttpClientResponse response) throws Exception {
    if (HttpStatus.SC_BAD_REQUEST < response.getStatusCode()) {
      return handleError(response);
    }
    return convertResult(response, this.responseType);
  }

  private HttpRestResult<T> handleError(HttpClientResponse response) throws Exception {
    String message =
        CharStreams.toString(new InputStreamReader(response.getBody(), Charsets.UTF_8));
    return new HttpRestResult<>(response.getStatusCode(), message, null, false, true);
  }

  public HttpRestResult<T> convertResult(HttpClientResponse response, Class<T> responseType)
      throws Exception {
    InputStream body = response.getBody();
    return JacksonUtils.parseObject(body, new TypeReferenceHttpResult<>(responseType));
  }
}
