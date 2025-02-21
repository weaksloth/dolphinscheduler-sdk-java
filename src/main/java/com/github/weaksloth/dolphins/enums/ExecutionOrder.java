package com.github.weaksloth.dolphins.enums;

/** complement data in some kind of order */
public enum ExecutionOrder {

  /** 0 complement data in descending order 1 complement data in ascending order */
  DESC_ORDER(0, "descending order"),
  ASC_ORDER(1, "ascending order");

  ExecutionOrder(int code, String desc) {
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
