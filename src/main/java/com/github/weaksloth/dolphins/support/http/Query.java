package com.github.weaksloth.dolphins.support.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** query param for get option */
public class Query {

  private Map<String, String> params;

  public Query() {
    params = new LinkedHashMap<>();
  }

  public Query addParam(String key, String value) {
    params.put(key, value);
    return this;
  }

  public Object getValue(String key) {
    return params.get(key);
  }

  public boolean isEmpty() {
    return params.isEmpty();
  }

  /**
   * Print query as a http url param string. Like K=V&K=V.
   *
   * @return http url param string
   */
  public String toQueryUrl() {
    StringBuilder urlBuilder = new StringBuilder();
    Set<Map.Entry<String, String>> entrySet = params.entrySet();
    int i = entrySet.size();
    for (Map.Entry<String, String> entry : entrySet) {
      try {
        if (null != entry.getValue()) {
          urlBuilder
              .append(entry.getKey())
              .append('=')
              .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.displayName()));
          if (i > 1) {
            urlBuilder.append('&');
          }
        }
        i--;
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }

    return urlBuilder.toString();
  }
}
