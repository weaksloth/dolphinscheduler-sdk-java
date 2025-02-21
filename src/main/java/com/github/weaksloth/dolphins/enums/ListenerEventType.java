package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum ListenerEventType {
  SERVER_DOWN(0, "SERVER_DOWN"),
  PROCESS_DEFINITION_CREATED(1, "PROCESS_DEFINITION_CREATED"),
  PROCESS_DEFINITION_UPDATED(2, "PROCESS_DEFINITION_UPDATED"),
  PROCESS_DEFINITION_DELETED(3, "PROCESS_DEFINITION_DELETED"),
  PROCESS_START(4, "PROCESS_START"),
  PROCESS_END(5, "PROCESS_INSTANCE_END"),
  PROCESS_FAIL(6, "PROCESS_FAIL"),
  TASK_START(10, "TASK_START"),
  TASK_END(11, "TASK_END"),
  TASK_FAIL(12, "TASK_FAIL");

  private static final Map<Integer, ListenerEventType> CODE_MAP = new HashMap<>();

  static {
    for (ListenerEventType listenerEventType : ListenerEventType.values()) {
      CODE_MAP.put(listenerEventType.getCode(), listenerEventType);
    }
  }

  private final int code;
  private final String descp;

  ListenerEventType(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  public static ListenerEventType of(int code) {
    ListenerEventType listenerEventType = CODE_MAP.get(code);
    if (listenerEventType == null) {
      throw new IllegalArgumentException(
          String.format("The task execution status code: %s is invalid", code));
    }
    return listenerEventType;
  }
}
