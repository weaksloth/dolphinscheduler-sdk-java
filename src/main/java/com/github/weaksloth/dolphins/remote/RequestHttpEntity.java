package com.github.weaksloth.dolphins.remote;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RequestHttpEntity {

  private Header header;

  private Object body;

  private Query query;

  public RequestHttpEntity() {}

  public RequestHttpEntity(Header header, Query query) {
    this.header = header;
    this.query = query;
  }

  /**
   * object to map by reflect
   *
   * @param object
   * @return map
   */
  public Map<String, String> objToMap(Object object) {
    Map<String, String> map = new LinkedHashMap<>();
    Field[] declaredFields = object.getClass().getDeclaredFields();
    for (Field field : declaredFields) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(object).toString());
      } catch (IllegalAccessException e) {
        log.error("object to map fail", e);
      }
    }
    return map;
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
}
