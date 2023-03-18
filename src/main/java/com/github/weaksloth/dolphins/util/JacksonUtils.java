package com.github.weaksloth.dolphins.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/** json utils based on jackson */
public class JacksonUtils {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static ObjectNode createObjectNode() {
    return mapper.createObjectNode();
  }

  public static ArrayNode createArrayNode() {
    return mapper.createArrayNode();
  }

  /**
   * parse json string as json node
   *
   * @param jsonStr json string
   * @return {@link JsonNode}
   */
  public static JsonNode parseNode(String jsonStr) {
    try {
      return mapper.readTree(jsonStr);
    } catch (IOException e) {
      throw new RuntimeException("parse json node fail", e);
    }
  }

  /**
   * search json node by path expression
   *
   * @param jsonNode json node
   * @param path path expression, json example: {"k1":{"k2":"v2"},"k3":"v3"}，we can get "v2" by path
   *     "k1.k2", we can get "v3" by path "k3"
   * @return
   */
  private static JsonNode searchJsonNode(JsonNode jsonNode, String path) {
    try {
      String[] split = path.split("\\.");
      for (String key : split) {
        jsonNode = jsonNode.get(key);
      }
      return jsonNode;

    } catch (Exception e) {
      throw new IllegalArgumentException("parse json error", e);
    }
  }

  /**
   * @param jsonStr json string
   * @param path path expression, json example: {"k1":{"k2":"v2"},"k3":"v3"}，we can get "v2" by path
   *     "k1.k2", we can get "v3" by path "k3"
   * @return
   */
  private static JsonNode searchJsonNode(String jsonStr, String path) {
    return searchJsonNode(parseNode(jsonStr), path);
  }

  public static ArrayNode getAsArrayNode(String jsonStr, String path) {
    JsonNode arrayNode = searchJsonNode(jsonStr, path);
    if (!arrayNode.isArray()) {
      throw new RuntimeException("path:" + path + " is not a json array");
    }
    return (ArrayNode) arrayNode;
  }

  public static ArrayNode getAsArrayNode(JsonNode jsonNode, String path) {
    JsonNode arrayNode = searchJsonNode(jsonNode, path);
    if (!arrayNode.isArray()) {
      throw new RuntimeException("path:" + path + " is not a json array");
    }
    return (ArrayNode) arrayNode;
  }

  /**
   * get value as string
   *
   * @param jsonStr json string
   * @param path path expression
   * @return
   */
  public static String getAsText(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).textValue();
  }

  /**
   * get value as string
   *
   * @param jsonNode json node
   * @param path path expression
   * @return
   */
  public static String getAsText(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).textValue();
  }

  /**
   * get value as long
   *
   * @param jsonStr json string
   * @param path path expression
   * @return
   */
  public static Long getAsLong(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).longValue();
  }

  /**
   * get value as long
   *
   * @param jsonNode json node
   * @param path path expression
   * @return
   */
  public static Long getAsLong(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).longValue();
  }

  /**
   * get value as int
   *
   * @param jsonStr json string
   * @param path path expression
   * @return
   */
  public static Integer getAsInteger(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).intValue();
  }

  /**
   * get value as int
   *
   * @param jsonNode json node
   * @param path path expression
   * @return
   */
  public static Integer getAsInteger(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).intValue();
  }

  /**
   * get value as boolean
   *
   * @param jsonStr json string
   * @param path path expression
   * @return
   */
  public static Boolean getAsBoolean(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).booleanValue();
  }

  /**
   * get value as boolean
   *
   * @param jsonNode json node
   * @param path path expression
   * @return
   */
  public static Boolean getAsBoolean(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).booleanValue();
  }

  public static String toJSONString(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("transfer to json string fail", e);
    }
  }

  /**
   * @param json json str
   * @param clazz target class
   * @param <T>
   * @return
   */
  public static <T> T parseObject(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("json parse object fail", e);
    }
  }

  /**
   * @param json json string
   * @param typeReference type of object
   * @param <T>
   * @return
   */
  public static <T> T parseObject(String json, TypeReference<T> typeReference) {
    try {
      return mapper.readValue(json, typeReference);
    } catch (IOException e) {
      throw new RuntimeException("json parse object fail", e);
    }
  }

  /**
   * Json string deserialize to Object.
   *
   * @param json json string
   * @param type {@link Type} of object
   * @param <T> General type
   * @return object
   * @throws RuntimeException if deserialize failed
   */
  public static <T> T parseObject(String json, Type type) {
    try {
      return mapper.readValue(json, mapper.constructType(type));
    } catch (IOException e) {
      throw new RuntimeException("json parse object fail", e);
    }
  }

  /**
   * Json string deserialize to Object.
   *
   * @param inputStream json string input stream
   * @param type {@link Type} of object
   * @param <T> General type
   * @return object
   * @throws RuntimeException if deserialize failed
   */
  public static <T> T parseObject(InputStream inputStream, Type type) {
    try {
      return mapper.readValue(inputStream, mapper.constructType(type));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Json string deserialize to Object.
   *
   * @param inputStream json string input stream
   * @param typeReference {@link Type} of object
   * @param <T> General type
   * @return object
   * @throws RuntimeException if deserialize failed
   */
  public static <T> T parseObject(InputStream inputStream, TypeReference<T> typeReference) {
    try {
      return mapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static ObjectMapper getObjectMapper() {
    return mapper;
  }
}
