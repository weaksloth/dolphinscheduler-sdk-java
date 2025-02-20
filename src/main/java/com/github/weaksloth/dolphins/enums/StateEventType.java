package com.github.weaksloth.dolphins.enums;

public enum StateEventType {
  PROCESS_STATE_CHANGE(0, "process state change"),
  TASK_STATE_CHANGE(1, "task state change"),
  PROCESS_TIMEOUT(2, "process timeout"),
  TASK_TIMEOUT(3, "task timeout"),
  WAKE_UP_TASK_GROUP(4, "wait task group"),
  TASK_RETRY(5, "task retry"),
  PROCESS_BLOCKED(6, "process blocked"),
  PROCESS_SUBMIT_FAILED(7, "process submit failed");

  StateEventType(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  private final int code;
  private final String descp;

  public int getCode() {
    return code;
  }

  public String getDescp() {
    return descp;
  }
}
