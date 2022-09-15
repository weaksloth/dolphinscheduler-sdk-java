package com.github.weaksloth.dolphins.support.http.response;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface HttpClientResponse extends Closeable {

  Map<String, String> getHeaders();

  InputStream getBody() throws IOException;

  int getStatusCode();
}
