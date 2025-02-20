package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;

/** Audit Module type */
public enum AuditResourceType {

  // TODO: add other audit resource enums
  USER_MODULE(0, "USER"),
  PROJECT_MODULE(1, "PROJECT");

  private final int code;
  private final String enMsg;

  private static HashMap<Integer, AuditResourceType> AUDIT_RESOURCE_MAP = new HashMap<>();

  static {
    for (AuditResourceType auditResourceType : AuditResourceType.values()) {
      AUDIT_RESOURCE_MAP.put(auditResourceType.code, auditResourceType);
    }
  }

  AuditResourceType(int code, String enMsg) {
    this.code = code;
    this.enMsg = enMsg;
  }

  public int getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.enMsg;
  }

  public static AuditResourceType of(int status) {
    if (AUDIT_RESOURCE_MAP.containsKey(status)) {
      return AUDIT_RESOURCE_MAP.get(status);
    }
    throw new IllegalArgumentException("invalid audit resource type code " + status);
  }
}
