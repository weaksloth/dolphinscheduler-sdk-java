package com.github.weaksloth.dolphins.enums;

/** task node depend type */
public enum TaskDependType {

  /**
   * 0 run current tasks only 1 run current tasks and previous tasks 2 run current tasks and the
   * other tasks that depend on current tasks;
   */
  TASK_ONLY(0, "task only"),
  TASK_PRE(1, "task pre"),
  TASK_POST(2, "task post");

  TaskDependType(int code, String descp) {
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
