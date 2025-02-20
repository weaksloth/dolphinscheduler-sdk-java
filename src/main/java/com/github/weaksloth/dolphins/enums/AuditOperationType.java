package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;

/** Audit Operation type */
public enum AuditOperationType {
  CREATE(0, "CREATE"),
  READ(1, "READ"),
  UPDATE(2, "UPDATE"),
  DELETE(3, "DELETE");

  private final int code;
  private final String enMsg;

  private static HashMap<Integer, AuditOperationType> AUDIT_OPERATION_MAP = new HashMap<>();

  static {
    for (AuditOperationType operationType : AuditOperationType.values()) {
      AUDIT_OPERATION_MAP.put(operationType.code, operationType);
    }
  }

  AuditOperationType(int code, String enMsg) {
    this.code = code;
    this.enMsg = enMsg;
  }

  public static AuditOperationType of(int status) {
    if (AUDIT_OPERATION_MAP.containsKey(status)) {
      return AUDIT_OPERATION_MAP.get(status);
    }
    throw new IllegalArgumentException("invalid audit operation type code " + status);
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return enMsg;
  }
}
