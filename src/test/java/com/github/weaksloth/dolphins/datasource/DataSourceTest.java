package com.github.weaksloth.dolphins.datasource;

import org.junit.Test;

public class DataSourceTest {

  @Test
  public void createDataSource() {
    DataSourceCreateParam dataSourceCreateParam = new DataSourceCreateParam();
    dataSourceCreateParam
        .setUserName("root")
        .setPassword("root")
        .setDatabase("test")
        .setPort("3306")
        .setName("ds-create-test")
        .setType("ORACLE")
        .setHost("localhost");
  }

  @Test
  public void listDataSource() {}
}
