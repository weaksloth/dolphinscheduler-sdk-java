package com.github.weaksloth.dolphins.enums;

import static java.util.stream.Collectors.toMap;

import com.google.common.base.Functions;
import java.util.Arrays;
import java.util.Map;

/** types for whether to send warning when process ends; */
public enum WarningType {

  /**
   * 0 do not send warning; 1 send if process success; 2 send if process failed; 3 send if process
   * ends, whatever the result; 4 send global events;
   */
  NONE(0, "none"),
  SUCCESS(1, "success"),
  FAILURE(2, "failure"),
  ALL(3, "all"),
  GLOBAL(4, "global");

  WarningType(int code, String descp) {
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

  private static final Map<String, WarningType> WARNING_TYPE_MAP =
      Arrays.stream(WarningType.values())
          .collect(toMap(WarningType::getDescp, Functions.identity()));

  public static WarningType of(String descp) {
    if (WARNING_TYPE_MAP.containsKey(descp)) {
      return WARNING_TYPE_MAP.get(descp);
    }
    return null;
  }
}
