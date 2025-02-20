package com.github.weaksloth.dolphins.enums;

public enum BlockingOpportunity {
  BLOCKING_ON_SUCCESS("BlockingOnSuccess"),
  BLOCKING_ON_FAILED("BlockingOnFailed");

  private final String desc;

  BlockingOpportunity(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
