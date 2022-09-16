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

  public static ObjectNode createEmptyObjectNode() {
    return mapper.createObjectNode();
  }

  public static ArrayNode createEmptyArrayNode() {
    return mapper.createArrayNode();
  }

  /**
   * 解析字符串为jsonNode
   *
   * @param jsonStr 待解析的json字符串
   * @return
   */
  public static JsonNode parseNode(String jsonStr) {
    try {
      return mapper.readTree(jsonStr);
    } catch (IOException e) {
      throw new RuntimeException("parse json node fail", e);
    }
  }

  /**
   * 根据path检索json节点
   *
   * @param jsonNode json节点
   * @param path 路径表达式 {"k1":"v1","k2":"v2"}，如果要获取k1，那么path填写方式为：k1
   *     {"k1":{"k2":"v2"},"k3":"v3"},如果要获取k2,那么path填写方式为: k1.k2
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
   * @param jsonStr json字符串
   * @param path key表达式 {"k1":"v1","k2":"v2"}，如果要获取k1，那么path填写方式为：k1
   *     {"k1":{"k2":"v2"},"k3":"v3"},如果要获取k2,那么path填写方式为: k1.k2
   * @return
   */
  private static JsonNode searchJsonNode(String jsonStr, String path) {
    return searchJsonNode(parseNode(jsonStr), path);
  }

  /**
   * 根据path获取json数据类型的节点，如果找到的节点不是json数组会抛出异常
   *
   * @param jsonStr json字符串
   * @param path 路径表达式
   * @return
   */
  public static ArrayNode getAsArrayNode(String jsonStr, String path) {
    JsonNode arrayNode = searchJsonNode(jsonStr, path);
    if (!arrayNode.isArray()) {
      throw new RuntimeException("path:" + path + " is not a json array");
    }
    return (ArrayNode) arrayNode;
  }

  /**
   * 根据path获取json数组类型的节点，如果不是json数据会抛出异常
   *
   * @param jsonNode
   * @param path
   * @return
   */
  public static ArrayNode getAsArrayNode(JsonNode jsonNode, String path) {
    JsonNode arrayNode = searchJsonNode(jsonNode, path);
    if (!arrayNode.isArray()) {
      throw new RuntimeException("path:" + path + " is not a json array");
    }
    return (ArrayNode) arrayNode;
  }

  /**
   * 获取字符串类型的值
   *
   * @param jsonStr json字符串
   * @param path 表达式
   * @return
   */
  public static String getAsText(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).textValue();
  }

  /**
   * 获取字符串类型的值
   *
   * @param jsonNode json节点
   * @param path 路径表达式
   * @return
   */
  public static String getAsText(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).textValue();
  }

  /**
   * 获取long类型的值
   *
   * @param jsonStr json字符串
   * @param path 表达式
   * @return
   */
  public static Long getAsLong(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).longValue();
  }

  /**
   * 获取Long类型的值
   *
   * @param jsonNode json节点
   * @param path 路径表达式
   * @return
   */
  public static Long getAsLong(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).longValue();
  }

  /**
   * 获取int类型的值
   *
   * @param jsonStr json字符串
   * @param path 表达式
   * @return
   */
  public static Integer getAsInteger(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).intValue();
  }

  /**
   * 获取int类型的值
   *
   * @param jsonNode json节点
   * @param path 路径表达式
   * @return
   */
  public static Integer getAsInteger(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).intValue();
  }

  /**
   * 获取布尔类型的值
   *
   * @param jsonStr json字符串
   * @param path 表达式
   * @return
   */
  public static Boolean getAsBoolean(String jsonStr, String path) {
    return searchJsonNode(jsonStr, path).booleanValue();
  }

  /**
   * 获取布尔类型的值
   *
   * @param jsonNode json节点
   * @param path 路径表达式
   * @return
   */
  public static Boolean getAsBoolean(JsonNode jsonNode, String path) {
    return searchJsonNode(jsonNode, path).booleanValue();
  }

  /**
   * 将对象转换为json字符串
   *
   * @param object
   * @return
   */
  public static String toJSONString(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("transfer to json string fail", e);
    }
  }

  /**
   * @param json json字符串
   * @param clazz 类
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
