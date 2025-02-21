package com.github.weaksloth.dolphins.enums;

public enum SqoopQueryType {
  FORM(0, "SQOOP_QUERY_FORM"),
  SQL(1, "SQOOP_QUERY_SQL");

  private final Integer code;

  private final String desc;

  SqoopQueryType(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public Integer getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
