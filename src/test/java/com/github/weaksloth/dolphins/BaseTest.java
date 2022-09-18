package com.github.weaksloth.dolphins;

import com.github.weaksloth.dolphins.core.DolphinClient;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.Header;
import com.github.weaksloth.dolphins.remote.request.DefaultHttpClientRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.RequestContent;

public class BaseTest {

  protected final String dolphinAddress = "http://localhost:12345/dolphinscheduler";
  protected final Long projectCode = 6919898920896L;
  private final String token = "0df5f42218fa114eed24014bf4efc085";
  protected final String tenantCode = "chen";

  protected DolphinsRestTemplate restTemplate =
      new DolphinsRestTemplate(
          new DefaultHttpClientRequest(
              HttpClients.custom()
                  .addInterceptorLast(new RequestContent(true))
                  .setDefaultRequestConfig(RequestConfig.custom().build())
                  .build(),
              RequestConfig.custom().build()));

  protected DolphinClient getClient() {
    return new DolphinClient(token, dolphinAddress, restTemplate);
  }

  protected Header getHeader() {
    Header header = Header.newInstance();
    header.addParam("token", token);
    return header;
  }
}
