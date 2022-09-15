package com.github.weaksloth.dolphins.remote;

import com.github.weaksloth.dolphins.remote.handler.ResponseHandler;
import com.github.weaksloth.dolphins.remote.request.HttpClientRequest;
import com.github.weaksloth.dolphins.remote.response.HttpClientResponse;
import com.github.weaksloth.dolphins.util.HttpUtils;
import java.net.URI;

public class DolphinsRestTemplate {

  private final HttpClientRequest requestClient;

  public DolphinsRestTemplate(HttpClientRequest requestClient) {
    this.requestClient = requestClient;
  }

  public <T> HttpRestResult<T> get(String url, Header header, Query query, Class<T> responseType)
      throws Exception {
    return execute(url, HttpMethod.GET, new RequestHttpEntity(header, query), responseType);
  }

  private <T> HttpRestResult<T> execute(
      String url, String httpMethod, RequestHttpEntity requestEntity, Class<T> responseType)
      throws Exception {
    URI uri = HttpUtils.buildUri(url, requestEntity.getQuery());

    ResponseHandler<T> responseHandler = new ResponseHandler<>();
    responseHandler.setResponseType(responseType);
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
