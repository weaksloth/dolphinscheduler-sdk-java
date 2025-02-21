package com.github.weaksloth.dolphins.enums;

/** trigger support type */
public enum ApiTriggerType {
  PROCESS(0, "process instance"),
  TASK(1, "task node"),
  COMMAND(2, "command");

  ApiTriggerType(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private final int code;
  private final String desc;

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
