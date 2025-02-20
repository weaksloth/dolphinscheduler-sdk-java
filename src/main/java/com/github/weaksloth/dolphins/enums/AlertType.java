package com.github.weaksloth.dolphins.enums;

/** describe the reason why alert generates */
public enum AlertType {

  /**
   * 0 process instance failure, 1 process instance success, 2 process instance blocked, 3 process
   * instance timeout, 4 fault tolerance warning, 5 task failure, 6 task success, 7 task timeout, 8
   * close alert
   */
  PROCESS_INSTANCE_FAILURE(0, "process instance failure"),
  PROCESS_INSTANCE_SUCCESS(1, "process instance success"),
  PROCESS_INSTANCE_BLOCKED(2, "process instance blocked"),
  PROCESS_INSTANCE_TIMEOUT(3, "process instance timeout"),
  FAULT_TOLERANCE_WARNING(4, "fault tolerance warning"),
  TASK_FAILURE(5, "task failure"),
  TASK_SUCCESS(6, "task success"),
  TASK_TIMEOUT(7, "task timeout"),

  CLOSE_ALERT(8, "the process instance success, can close the before alert");

  AlertType(int code, String descp) {
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
