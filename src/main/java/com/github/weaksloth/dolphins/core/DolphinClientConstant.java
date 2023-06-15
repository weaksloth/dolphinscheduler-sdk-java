package com.github.weaksloth.dolphins.core;

public class DolphinClientConstant {

  public static class Page {
    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer DEFAULT_SIZE = 10;
  }

  public static class LogLimit {
    public static final Integer DEFAULT_SKIP = 0;
    public static final Integer DEFAULT_LIMIT = 50;
  }


  public static final String OFFLINE_RELEASE_STATE = "OFFLINE"; // 工作流下线状态
  public static final String ONLINE_RELEASE_STATE = "ONLINE"; // 工作流上线状态

  public static final String RE_RUN_EXECUTE_TYPE = "REPEAT_RUNNING";

  public static class HttpTask {
    public static final String HTTP_PARAMETER_TYPE_PARAMETER = "PARAMETER";
    public static final String HTTP_PARAMETER_TYPE_BODY = "BODY";
    public static final String HTTP_PARAMETER_TYPE_HEADERS = "HEADERS";

    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_PUT = "PUT";
  }

  public static class TaskType {
    public static final String DATAX = "DATAX";
    public static final String HTTP = "HTTP";
    public static final String CONDITION = "CONDITIONS";
    public static final String SHELL = "SHELL";
  }

  public static class Resource {
    public static final String TYPE_FILE = "FILE";
    public static final String TYPE_UDF = "UDF";
    public static final String DEFAULT_PID_FILE = "-1";
    public static final String DEFAULT_CURRENT_DIR = "/";
  }
}
