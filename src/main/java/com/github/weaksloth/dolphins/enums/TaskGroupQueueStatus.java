package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;

/** running status for task group queue */
public enum TaskGroupQueueStatus {
  WAIT_QUEUE(-1, "wait queue"),
  ACQUIRE_SUCCESS(1, "acquire success"),
  RELEASE(2, "release");

  private final int code;
  private final String descp;
  private static HashMap<Integer, TaskGroupQueueStatus> STATUS_MAP = new HashMap<>();

  static {
    for (TaskGroupQueueStatus taskGroupQueueStatus : TaskGroupQueueStatus.values()) {
      STATUS_MAP.put(taskGroupQueueStatus.code, taskGroupQueueStatus);
    }
  }

  TaskGroupQueueStatus(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  public static TaskGroupQueueStatus of(int status) {
    if (STATUS_MAP.containsKey(status)) {
      return STATUS_MAP.get(status);
    }
    throw new IllegalArgumentException("invalid status : " + status);
  }

  public int getCode() {
    return code;
  }

  public String getDescp() {
    return descp;
  }
}
