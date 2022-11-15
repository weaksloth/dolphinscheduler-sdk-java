package com.github.weaksloth.dolphins.datasource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.Query;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** operator for operate datasource */
@Slf4j
public class DataSourceOperator extends AbstractOperator {

  public DataSourceOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * create datasource, api:/dolphinscheduler/datasources
   *
   * @param dataSourceCreateParam create datasource param
   * @return true for success,otherwise false
   */
  public Boolean create(DataSourceCreateParam dataSourceCreateParam) {
    String url = dolphinAddress + "/datasources";
    try {
      HttpRestResult<String> result =
          dolphinsRestTemplate.postJson(url, getHeader(), dataSourceCreateParam, String.class);
      log.info("create datasource response:{}", result);
      return result.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("create dolphin scheduler datasource fail", e);
    }
  }

  /**
   * update datasource, api：/dolphinscheduler/datasources/{id}
   *
   * @param dataSourceUpdateParam update datasource param
   * @return true for success,otherwise false
   */
  public Boolean update(DataSourceUpdateParam dataSourceUpdateParam) {
    String url = dolphinAddress + "/datasources/" + dataSourceUpdateParam.getId();
    try {
      HttpRestResult<String> result =
          dolphinsRestTemplate.putJson(url, getHeader(), dataSourceUpdateParam, String.class);
      log.info("update datasource response:{}", result);
      return result.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("update dolphin scheduler datasource fail", e);
    }
  }

  /**
   * delete dolphin scheduler datasource
   *
   * @param id dolphin scheduler datasource id
   * @return true for success,otherwise false
   */
  public Boolean delete(Long id) {
    String url = dolphinAddress + "/datasources/" + id;
    try {
      HttpRestResult<String> result =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      log.info("delete datasource response:{}", result);
      return result.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler datasource fail", e);
    }
  }

  /**
   * page query dolphin datasource list ，api:/dolphinscheduler/datasources
   *
   * @return {@link List <DataSourceQueryResp>}
   */
  public List<DataSourceQueryResp> list(String dsName) {
    String url = dolphinAddress + "/datasources";
    Query query =
        new Query()
            .addParam("pageNo", "1")
            .addParam("pageSize", "10")
            .addParam("searchVal", dsName)
            .build();
    try {
      HttpRestResult<JsonNode> stringHttpRestResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);
      return JacksonUtils.parseObject(
              stringHttpRestResult.getData().toString(),
              new TypeReference<PageInfo<DataSourceQueryResp>>() {})
          .getTotalList();
    } catch (Exception e) {
      throw new DolphinException("list dolphin scheduler datasource fail", e);
    }
  }
}
