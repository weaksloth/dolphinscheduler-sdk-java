package com.github.weaksloth.dolphins.datasource;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.Query;
import org.junit.Test;

public class DataSourceTest extends BaseTest {

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

  /**
   * list all datasource
   *
   * @throws Exception
   */
  @Test
  public void listDataSource() throws Exception {
    Query query = new Query();
    query.addParam("pageNo", "1");
    query.addParam("pageSize", "10");
    query.addParam("searchVal", "");
    HttpRestResult<String> stringHttpRestResult =
        restTemplate.get(dolphinAddress + "/datasources", getHeader(), query, String.class);
    System.out.println(stringHttpRestResult);
  }
}
