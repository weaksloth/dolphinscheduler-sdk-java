package com.github.weaksloth.dolphins.enums;

/** user type */
public enum UserType {

  /** 0 admin user; 1 general user */
  ADMIN_USER(0, "admin user"),
  GENERAL_USER(1, "general user");

  UserType(int code, String descp) {
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
