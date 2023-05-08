package com.github.weaksloth.dolphins.schedule;

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

@Slf4j
public class ScheduleOperator extends AbstractOperator {

  public ScheduleOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * create schedule
   *
   * @param projectCode project code
   * @param scheduleDefineParam define param
   * @return {@link ScheduleInfoResp}
   */
  public ScheduleInfoResp create(Long projectCode, ScheduleDefineParam scheduleDefineParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules";
    log.info("create schedule, url:{}, defineParam:{}", url, scheduleDefineParam);
    try {
      HttpRestResult<ScheduleInfoResp> restResult =
          dolphinsRestTemplate.postForm(
              url, getHeader(), scheduleDefineParam, ScheduleInfoResp.class);
      if (restResult.getSuccess()) {
        return restResult.getData();
      } else {
        log.error("dolphin scheduler response:{}", restResult);
        throw new DolphinException("create dolphin scheduler schedule fail");
      }
    } catch (Exception e) {
      throw new DolphinException("create dolphin scheduler schedule fail", e);
    }
  }

  /**
   * get schedule by workflow
   *
   * @param projectCode project's code
   * @param processDefinitionCode workflow code
   * @return {@link List<ScheduleInfoResp>}
   */
  public List<ScheduleInfoResp> getByWorkflowCode(Long projectCode, Long processDefinitionCode) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules";
    Query query =
        new Query()
            .addParam("pageNo", "1")
            .addParam("pageSize", "10")
            .addParam("processDefinitionCode", String.valueOf(processDefinitionCode));
    try {
      HttpRestResult<JsonNode> stringHttpRestResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);
      return JacksonUtils.parseObject(
              stringHttpRestResult.getData().toString(),
              new TypeReference<PageInfo<ScheduleInfoResp>>() {})
          .getTotalList();
    } catch (Exception e) {
      throw new DolphinException("list dolphin scheduler schedule fail", e);
    }
  }

  /**
   * update schedule
   *
   * @param projectCode project code
   * @param scheduleDefineParam define param
   * @return {@link ScheduleInfoResp}
   */
  public ScheduleInfoResp update(
      Long projectCode, Long scheduleId, ScheduleDefineParam scheduleDefineParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId;
    log.info("update schedule, url:{}, defineParam:{}", url, scheduleDefineParam);
    try {
      HttpRestResult<ScheduleInfoResp> restResult =
          dolphinsRestTemplate.putForm(
              url, getHeader(), scheduleDefineParam, ScheduleInfoResp.class);
      if (restResult.getSuccess()) {
        return restResult.getData();
      } else {
        log.error("dolphin scheduler response:{}", restResult);
        throw new DolphinException("update dolphin scheduler schedule fail");
      }
    } catch (Exception e) {
      throw new DolphinException("update dolphin scheduler schedule fail", e);
    }
  }

  /**
   * online schedule
   *
   * @param projectCode project code
   * @param scheduleId id
   * @return true for success,otherwise false
   */
  public Boolean online(Long projectCode, Long scheduleId) {
    String url =
        dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId + "/online";
    log.info("online schedule, scheduleId:{}, url:{}", scheduleId, url);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("online dolphin scheduler schedule fail", e);
    }
  }

  /**
   * offline schedule
   *
   * @param projectCode project code
   * @param scheduleId id
   * @return true for success,otherwise false
   */
  public Boolean offline(Long projectCode, Long scheduleId) {
    String url =
        dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId + "/offline";
    log.info("offline schedule, scheduleId:{}, url:{}", scheduleId, url);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("offline dolphin scheduler schedule fail", e);
    }
  }

  /**
   * delete schedule
   *
   * @param projectCode project code
   * @param scheduleId id
   * @return true for success,otherwise false
   */
  public Boolean delete(Long projectCode, Long scheduleId) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId;
    log.info("offline schedule, scheduleId:{}, url:{}", scheduleId, url);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler schedule fail", e);
    }
  }
}
