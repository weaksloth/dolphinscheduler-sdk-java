package com.github.weaksloth.dolphins.enums;

public enum AlertPluginInstanceType {
  NORMAL(0, "NORMAL"),
  GLOBAL(1, "GLOBAL");

  AlertPluginInstanceType(int code, String descp) {
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
