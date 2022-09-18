package com.github.weaksloth.dolphins.core;

/** dolphin异常信息类 */
public class DolphinException extends RuntimeException {

  public static final String GENERATE_TASK_CODE_ERROR = "generate task code fail";

  public static final String CREATE_WORKFLOW_ERROR = "create dolphin scheduler workflow fail";
  public static final String UPDATE_WORKFLOW_ERROR = "update dolphin scheduler workflow fail";
  public static final String RELEASE_WORKFLOW_ERROR = "release dolphin scheduler workflow fail";
  public static final String DELETE_WORKFLOW_ERROR = "delete dolphin scheduler workflow fail";

  public static final String START_PROCESS_INSTANCE_ERROR =
      "start dolphin scheduler process instance fail";
  public static final String RERUN_PROCESS_INSTANCE_ERROR =
      "repeat run dolphin scheduler process instance fail";
  public static final String PAGE_PROCESS_INSTANCE_ERROR =
      "page dolphin scheduler process instance list fail";
  public static final String DELETE_PROCESS_INSTANCE_ERROR =
      "delete dolphin scheduler process instance fail";

  public static final String CREATE_SCHEDULE_ERROR = "create dolphin scheduler schedule fail";
  public static final String UPDATE_SCHEDULE_ERROR = "update dolphin scheduler schedule fail";
  public static final String ONLINE_SCHEDULE_ERROR = "online dolphin scheduler schedule fail";
  public static final String OFFLINE_SCHEDULE_ERROR = "offline dolphin scheduler schedule fail";
  public static final String DELETE_SCHEDULE_ERROR = "delete dolphin scheduler schedule fail";

  public static final String CREATE_DATASOURCE_ERROR = "create dolphin scheduler datasource fail";
  public static final String LIST_DATASOURCE_ERROR = "list dolphin scheduler datasource fail";
  public static final String UPDATE_DATASOURCE_ERROR = "update dolphin scheduler datasource fail";
  public static final String DELETE_DATASOURCE_ERROR = "delete dolphin scheduler datasource fail";

  public static final String LIST_RESOURCE_ERROR = "list dolphin scheduler resource fail";
  public static final String ONLINE_CREATE_RESOURCE_ERROR = "online create resource fail";
  public static final String ONLINE_UPDATE_RESOURCE_ERROR = "online update resource fail";
  public static final String DELETE_RESOURCE_ERROR = "delete resource fail";

  public DolphinException(String message) {
    super(message);
  }
}
