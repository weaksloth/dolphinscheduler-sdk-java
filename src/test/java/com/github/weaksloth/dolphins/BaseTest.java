package com.github.weaksloth.dolphins;

import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.Header;
import com.github.weaksloth.dolphins.remote.request.DefaultHttpClientRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.RequestContent;

public class BaseTest {

  protected String dolphinAddress = "http://localhost:12345/dolphinscheduler";

  protected DolphinsRestTemplate restTemplate =
      new DolphinsRestTemplate(
          new DefaultHttpClientRequest(
              HttpClients.custom()
                  .addInterceptorLast(new RequestContent(true))
                  .setDefaultRequestConfig(RequestConfig.custom().build())
                  .build(),
              RequestConfig.custom().build()));

  protected Header getHeader() {
    Header header = Header.newInstance();
    header.addParam("token", "f8dbe7c2f3a5952e2895f306399c6bf9");
    return header;
  }
}
