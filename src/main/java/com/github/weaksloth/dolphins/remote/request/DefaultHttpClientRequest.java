package com.github.weaksloth.dolphins.remote.request;

import com.github.weaksloth.dolphins.remote.BaseHttpMethod;
import com.github.weaksloth.dolphins.remote.Header;
import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.remote.response.DefaultHttpClientResponse;
import com.github.weaksloth.dolphins.remote.response.HttpClientResponse;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class DefaultHttpClientRequest implements HttpClientRequest {

  private final CloseableHttpClient client;

  private final RequestConfig defaultConfig;

  public DefaultHttpClientRequest(CloseableHttpClient client, RequestConfig defaultConfig) {
    this.client = client;
    this.defaultConfig = defaultConfig;
  }

  @Override
  public HttpClientResponse execute(URI uri, String httpMethod, RequestHttpEntity requestHttpEntity)
      throws Exception {
    HttpRequestBase request = build(uri, httpMethod, requestHttpEntity);
    CloseableHttpResponse closeableHttpResponse = client.execute(request);
    return new DefaultHttpClientResponse(closeableHttpResponse);
  }

  private HttpRequestBase build(URI uri, String httpMethod, RequestHttpEntity requestHttpEntity)
      throws Exception {
    BaseHttpMethod method = BaseHttpMethod.of(httpMethod);
    Header headers = requestHttpEntity.getHeader();
    final HttpRequestBase requestBase = method.init(uri.toString());
    this.initRequestHeader(requestBase, headers);
    if (MediaType.FORM_DATA.toString().equals(headers.getValue(HttpHeaders.CONTENT_TYPE))
        && requestHttpEntity.ifBodyIsMap()) {
      // set form data
      Map<String, String> form = requestHttpEntity.castBodyToMap();
      if (form != null && !form.isEmpty()) {
        List<NameValuePair> params = new ArrayList<>(form.size());
        for (Map.Entry<String, String> entry : form.entrySet()) {
          params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        if (requestBase instanceof HttpEntityEnclosingRequest) {
          HttpEntityEnclosingRequest request = (HttpEntityEnclosingRequest) requestBase;
          HttpEntity entity = new UrlEncodedFormEntity(params, headers.getCharset());
          request.setEntity(entity);
        }
      }
    } else {
    }

    requestBase.setConfig(defaultConfig);
    return requestBase;
  }

  private void initRequestHeader(HttpRequestBase request, Header headers) {
    Iterator<Map.Entry<String, String>> iterator = headers.iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      request.setHeader(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void close() throws IOException {
    client.close();
  }
}
