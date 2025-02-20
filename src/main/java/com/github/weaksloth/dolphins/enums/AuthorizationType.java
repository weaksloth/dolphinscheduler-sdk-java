package com.github.weaksloth.dolphins.enums;

/** Authorization type */
public enum AuthorizationType {

  /**
   * 0 RESOURCE_FILE_ID; 1 RESOURCE_FILE_NAME; 2 UDF_FILE; 3 DATASOURCE; 4 UDF; 5 PROJECTS; 6
   * WORKER_GROUP; 7 ALERT_GROUP; 8 ENVIRONMENT; 9 ACCESS_TOKEN; 10 QUEUE; 11 DATA_ANALYSIS; 12
   * K8S_NAMESPACE; 13 MONITOR; 14 ALERT_PLUGIN_INSTANCE; 15 TENANT; 16 USER; 17 Data_Quality;
   */
  @Deprecated
  RESOURCE_FILE_ID(0, "resource file id"),
  @Deprecated
  RESOURCE_FILE_NAME(1, "resource file name"),
  @Deprecated
  UDF_FILE(2, "udf file"),
  DATASOURCE(3, "data source"),
  UDF(4, "udf function"),
  PROJECTS(5, "projects"),
  WORKER_GROUP(6, "worker group"),
  ALERT_GROUP(7, "alert group"),
  ENVIRONMENT(8, "environment"),
  ACCESS_TOKEN(9, "access token"),
  QUEUE(10, "queue"),
  DATA_ANALYSIS(11, "data analysis"),
  K8S_NAMESPACE(12, "k8s namespace"),
  MONITOR(13, "monitor"),
  ALERT_PLUGIN_INSTANCE(14, "alert plugin instance"),
  TENANT(15, "tenant"),
  DATA_QUALITY(16, "data quality"),
  TASK_GROUP(17, "task group"),
  ;

  AuthorizationType(int code, String descp) {
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
