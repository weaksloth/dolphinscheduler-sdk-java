package com.github.weaksloth.dolphins.support.http.request;

import com.github.weaksloth.dolphins.support.http.RequestHttpEntity;
import com.github.weaksloth.dolphins.support.http.response.HttpClientResponse;
import java.io.Closeable;
import java.net.URI;

public interface HttpClientRequest extends Closeable {

  HttpClientResponse execute(URI uri, String httpMethod, RequestHttpEntity requestHttpEntity)
      throws Exception;
}
