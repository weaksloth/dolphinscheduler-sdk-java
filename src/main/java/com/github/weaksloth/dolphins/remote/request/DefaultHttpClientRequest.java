package com.github.weaksloth.dolphins.remote.request;

import com.github.weaksloth.dolphins.remote.BaseHttpMethod;
import com.github.weaksloth.dolphins.remote.Header;
import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.remote.response.DefaultHttpClientResponse;
import com.github.weaksloth.dolphins.remote.response.HttpClientResponse;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import java.io.File;
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
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
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
    Object body = requestHttpEntity.getBody();
    BaseHttpMethod method = BaseHttpMethod.of(httpMethod);
    Header headers = requestHttpEntity.getHeader();
    final HttpRequestBase requestBase = method.init(uri.toString());
    this.initRequestHeader(requestBase, headers);
    if (MediaType.FORM_DATA.toString().equals(headers.getValue(HttpHeaders.CONTENT_TYPE))) {
      // set form data
      Map<String, Object> form;
      if (requestHttpEntity.ifBodyIsMap()) {
        form = requestHttpEntity.castBodyToMap();
      } else {
        form = requestHttpEntity.bodyToMap();
      }

      if (form != null && !form.isEmpty()) {
        List<NameValuePair> params = new ArrayList<>(form.size());
        for (Map.Entry<String, Object> entry : form.entrySet()) {
          params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        if (requestBase instanceof HttpEntityEnclosingRequest) {
          HttpEntityEnclosingRequest request = (HttpEntityEnclosingRequest) requestBase;
          HttpEntity entity = new UrlEncodedFormEntity(params, headers.getCharset());
          request.setEntity(entity);
        }
      }
    } else if (ContentType.MULTIPART_FORM_DATA
        .getMimeType()
        .equals(headers.getValue(HttpHeaders.CONTENT_TYPE))) {

      Map<String, Object> form;
      if (requestHttpEntity.ifBodyIsMap()) {
        form = requestHttpEntity.castBodyToMap();
      } else {
        form = requestHttpEntity.bodyToMap();
      }

      if (form != null && !form.isEmpty()) {

        MultipartEntityBuilder entityBuilder =
            MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        for (Map.Entry<String, Object> entry : form.entrySet()) {
          if (entry.getValue() instanceof File) {
            File file = (File) entry.getValue();
            entityBuilder.addBinaryBody(
                entry.getKey(), file, ContentType.DEFAULT_BINARY, file.getName());
          } else {
            entityBuilder.addTextBody(
                entry.getKey(), entry.getValue().toString(), ContentType.DEFAULT_TEXT);
          }
        }
        if (requestBase instanceof HttpEntityEnclosingRequest) {
          HttpEntityEnclosingRequest request = (HttpEntityEnclosingRequest) requestBase;
          HttpEntity entity = entityBuilder.build();
          request.setEntity(entity);
        }
      }

    } else { // set json data
      if (requestBase instanceof HttpEntityEnclosingRequest) {
        HttpEntityEnclosingRequest request = (HttpEntityEnclosingRequest) requestBase;
        ContentType contentType =
            ContentType.create(MediaType.JSON_UTF_8.type(), headers.getCharset());
        HttpEntity entity;
        if (body instanceof byte[]) {
          entity = new ByteArrayEntity((byte[]) body, contentType);
        } else {
          entity =
              new StringEntity(
                  body instanceof String ? (String) body : JacksonUtils.toJSONString(body),
                  contentType);
        }
        request.setEntity(entity);
      }
    }

    requestBase.setConfig(defaultConfig);
    return requestBase;
  }

  private void initRequestHeader(HttpRequestBase request, Header headers) {
    Iterator<Map.Entry<String, String>> iterator = headers.iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();

      // we need to remove content-type header when upload file
      // otherwise the backend will throw 'Missing initial multi part boundary'
      if (entry.getValue().equals(ContentType.MULTIPART_FORM_DATA.getMimeType())) {
        continue;
      }

      request.setHeader(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void close() throws IOException {
    client.close();
  }
}
