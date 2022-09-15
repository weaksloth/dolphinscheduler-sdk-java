package com.github.weaksloth.dolphins.support.http;

import com.github.weaksloth.dolphins.support.http.handler.ResponseHandler;
import com.github.weaksloth.dolphins.support.http.request.HttpClientRequest;
import com.github.weaksloth.dolphins.support.http.response.HttpClientResponse;
import com.github.weaksloth.dolphins.util.HttpUtils;
import java.lang.reflect.Type;
import java.net.URI;

public class DolphinsRestTemplate {

  private final HttpClientRequest requestClient;

  public DolphinsRestTemplate(HttpClientRequest requestClient) {
    this.requestClient = requestClient;
  }

  public <T> HttpRestResult<T> get(String url, Header header, Query query, Type responseType)
      throws Exception {
    //        return execute(url, HttpMethod.GET, new RequestHttpEntity(header, query),
    // responseType);
    return null;
  }

  private <T> HttpRestResult<T> execute(
      String url, String httpMethod, RequestHttpEntity requestEntity) throws Exception {
    URI uri = HttpUtils.buildUri(url, requestEntity.getQuery());

    ResponseHandler<T> responseHandler = new ResponseHandler<>();
    HttpClientResponse response = null;
    try {
      response = this.requestClient.execute(uri, httpMethod, requestEntity);
      return responseHandler.handle(response);
    } finally {
      if (response != null) {
        response.close();
      }
    }
  }
}
