package com.github.weaksloth.dolphins.enums;

/** UDF type */
public enum UdfType {

  /** 0 hive; 1 spark */
  HIVE(0, "hive"),
  SPARK(1, "spark");

  UdfType(int code, String descp) {
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

  public static UdfType of(int type) {
    for (UdfType ut : values()) {
      if (ut.getCode() == type) {
        return ut;
      }
    }
    throw new IllegalArgumentException("invalid type : " + type);
  }
}
