package com.github.weaksloth.dolphins.remote.request;

import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.remote.response.HttpClientResponse;
import java.io.Closeable;
import java.net.URI;

public interface HttpClientRequest extends Closeable {

  HttpClientResponse execute(URI uri, String httpMethod, RequestHttpEntity requestHttpEntity)
      throws Exception;
}
