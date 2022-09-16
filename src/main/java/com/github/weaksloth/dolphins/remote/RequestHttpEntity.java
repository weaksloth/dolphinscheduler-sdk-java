package com.github.weaksloth.dolphins.remote;

import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RequestHttpEntity {

  private Header header;

  private Query query;

  private Object body;

  public RequestHttpEntity() {}

  public RequestHttpEntity(Header header, Query query) {
    this.header = header;
    this.query = query;
  }

  public RequestHttpEntity(Header header, Query query, Object body) {
    this.header = header;
    this.query = query;
    this.body = body;
  }

  public RequestHttpEntity(Header header, Object body) {
    this.header = header;
    this.body = body;
  }

  /**
   * cast object to map when the object is instance of map
   *
   * @return
   */
  public Map<String, String> castBodyToMap() {
    if (ifBodyIsMap()) {
      return (Map<String, String>) body;
    }
    throw new UnsupportedOperationException(
        "the body is not instance of map,do not use this method");
  }

  /**
   * judge if body instance of map
   *
   * @return
   */
  public boolean ifBodyIsMap() {
    return body instanceof Map;
  }

  /**
   * param object to json string
   *
   * @return
   */
  public String bodyToJson() {
    return JacksonUtils.toJSONString(this.body);
  }

  /**
   * param object to map by reflect
   *
   * @return map
   */
  public Map<String, String> bodyToMap() {
    Map<String, String> map = new LinkedHashMap<>();
    Field[] declaredFields = this.body.getClass().getDeclaredFields();
    for (Field field : declaredFields) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(this.body).toString());
      } catch (IllegalAccessException e) {
        log.error("object to map fail", e);
      }
    }
    return map;
  }
}
