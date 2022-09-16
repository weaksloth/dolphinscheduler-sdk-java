package com.github.weaksloth.dolphins.core;

/** dolphin异常信息类 */
public class DolphinException extends RuntimeException {

  public static final String GENERATE_TASK_CODE_ERROR = "generate task code fail";

  public static final String CREATE_DATASOURCE_ERROR = "create dolphin scheduler datasource fail";
  public static final String UPDATE_DATASOURCE_ERROR = "update dolphin scheduler datasource fail";
  public static final String DELETE_DATASOURCE_ERROR = "delete dolphin scheduler datasource fail";

  public DolphinException(String message) {
    super(message);
  }
}
