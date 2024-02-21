package com.github.weaksloth.dolphins.datasource;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.enums.DbTypeEnum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class DataSourceTest extends BaseTest {

  /** create datasource */
  @Test
  public void createDataSource() {
    DataSourceCreateParam dataSourceCreateParam = new DataSourceCreateParam();
    Map<String, Object> map = new HashMap<>();
    map.put("useSSL", "false");
    dataSourceCreateParam
        .setUserName("root")
        .setPassword("xxxxxx") // use your own db info
        .setDatabase("test")
        .setPort("3306")
        .setName("ds-create-test2")
        .setType(DbTypeEnum.MYSQL.name())
        .setHost("localhost")
        .setOther(map);
    Assert.assertTrue(getClient().opsForDataSource().create(dataSourceCreateParam));
  }

  /** list all datasource */
  @Test
  public void listDataSource() {
    System.out.println(getClient().opsForDataSource().list(null));
  }

  /** update datasource */
  @Test
  public void updateDataSource() {
    List<DataSourceQueryResp> dataSources = getClient().opsForDataSource().list(null);
    DataSourceUpdateParam dataSourceUpdateParam = new DataSourceUpdateParam();
    dataSourceUpdateParam
        .setId(dataSources.get(0).getId()) // this id from create
        .setUserName("root")
        .setPassword("xxxxxx")
        .setDatabase("test")
        .setPort("3306")
        .setName("ds-create-test")
        .setNote("this note is generate by update api")
        .setType(DbTypeEnum.MYSQL.name())
        .setHost("localhost");
    Assert.assertTrue(getClient().opsForDataSource().update(dataSourceUpdateParam));
  }

  @Test
  public void deleteDataSource() {
    List<DataSourceQueryResp> dataSources = getClient().opsForDataSource().list(null);
    Assert.assertTrue(getClient().opsForDataSource().delete(dataSources.get(0).getId()));
  }
}
