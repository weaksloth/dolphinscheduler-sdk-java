package com.github.weaksloth.dolphins.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.lang.reflect.Type;

public class TypeReferenceHttpResult<T> extends TypeReference<HttpRestResult<T>> {

  protected final Type type;

  public TypeReferenceHttpResult(Class<?>... clazz) {
    type =
        JacksonUtils.getObjectMapper()
            .getTypeFactory()
            .constructParametricType(HttpRestResult.class, clazz);
  }

  @Override
  public Type getType() {
    return type;
  }
}
