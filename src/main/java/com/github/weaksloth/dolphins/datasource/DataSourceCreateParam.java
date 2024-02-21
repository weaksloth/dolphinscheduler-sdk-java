package com.github.weaksloth.dolphins.datasource;

import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/** create datasource param */
@Data
@Accessors(chain = true)
public class DataSourceCreateParam {

  private String database;

  private String host;

  private String name;

  private String userName;

  private String password;

  private String type;

  private String port;

  private String note;

  /**
   * this param is useful when creating oracle datasource
   *
   * <p>example value: ORACLE_SERVICE_NAME
   */
  private String connectType;

  /** jdbc connect params, json example: {"useServerPrepStmts":"true","useSSL":"false"} */
  private Map<String, Object> other;
}
