package com.github.weaksloth.dolphins.datasource;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.enums.DbTypeEnum;
import org.junit.Assert;
import org.junit.Test;

public class DataSourceTest extends BaseTest {

  /** create datasource */
  @Test
  public void createDataSource() {
    DataSourceCreateParam dataSourceCreateParam = new DataSourceCreateParam();
    dataSourceCreateParam
        .setUserName("root")
        .setPassword("xxxxxx") // use your own db info
        .setDatabase("test")
        .setPort("3306")
        .setName("ds-create-test")
        .setType(DbTypeEnum.MYSQL.name())
        .setHost("localhost");
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
    DataSourceUpdateParam dataSourceUpdateParam = new DataSourceUpdateParam();
    dataSourceUpdateParam
        .setId(10L) // this id from create
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
    Assert.assertTrue(getClient().opsForDataSource().delete(10L));
  }
}
