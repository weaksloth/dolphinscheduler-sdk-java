package com.github.weaksloth.dolphins.datasource;

import lombok.Data;
import lombok.experimental.Accessors;

/** update datasource param */
@Data
@Accessors(chain = true)
public class DataSourceUpdateParam {

  /** datasource id */
  private Long id;

  private String database;

  private String host;

  private String name;

  private String userName;

  private String password;

  private String type;

  private String port;

  private String note;
}
