package com.github.weaksloth.dolphins.enums;

public enum SqoopJobType {
  CUSTOM(0, "CUSTOM"),
  TEMPLATE(1, "TEMPLATE");

  SqoopJobType(int code, String descp) {
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
