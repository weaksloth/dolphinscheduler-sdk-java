package com.github.weaksloth.dolphins.remote;

import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Header {

  private static final String DEFAULT_CHARSET = "UTF-8";

  private static final Header EMPTY = Header.newInstance();

  private Map<String, String> header;

  private Header() {
    header = new LinkedHashMap<>();
    addParam(HttpHeaders.ACCEPT_CHARSET, DEFAULT_CHARSET);
    addParam(HttpHeaders.CONTENT_TYPE, MediaType.FORM_DATA.toString());
  }

  public static Header newInstance() {
    return new Header();
  }

  public Header addParam(String key, String value) {
    if (!Strings.isNullOrEmpty(key)) {
      header.put(key, value);
    }
    return this;
  }

  public Header setContentType(String contentType) {
    if (contentType == null) {
      contentType = MediaType.FORM_DATA.toString();
    }
    return addParam(HttpHeaders.CONTENT_TYPE, contentType);
  }

  public Header build() {
    return this;
  }

  public String getValue(String key) {
    return header.get(key);
  }

  public Iterator<Map.Entry<String, String>> iterator() {
    return header.entrySet().iterator();
  }

  public String getCharset() {
    String acceptCharset = getValue(HttpHeaders.ACCEPT_CHARSET);
    if (acceptCharset == null) {
      String contentType = getValue(HttpHeaders.CONTENT_TYPE);
      acceptCharset =
          !Strings.isNullOrEmpty(contentType)
              ? analysisCharset(contentType)
              : StandardCharsets.UTF_8.displayName();
    }
    return acceptCharset;
  }

  private String analysisCharset(String contentType) {
    String[] values = contentType.split(";");
    String charset = StandardCharsets.UTF_8.displayName();
    if (values.length == 0) {
      return charset;
    }
    for (String value : values) {
      if (value.startsWith("charset=")) {
        charset = value.substring("charset=".length());
      }
    }
    return charset;
  }
}
