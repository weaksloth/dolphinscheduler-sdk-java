package com.github.weaksloth.dolphins.scheduler;

import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchedulerOperator extends AbstractOperator {

  public SchedulerOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * create schedule
   *
   * @param projectCode project code
   * @param schedulerDefineParam define param
   * @return {@link SchedulerDefineResp}
   */
  public SchedulerDefineResp create(Long projectCode, SchedulerDefineParam schedulerDefineParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules";

    try {
      HttpRestResult<SchedulerDefineResp> restResult =
          dolphinsRestTemplate.postForm(
              url, getHeader(), schedulerDefineParam, SchedulerDefineResp.class);
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
   * update schedule
   *
   * @param projectCode project code
   * @param schedulerDefineParam define param
   * @return {@link SchedulerDefineResp}
   */
  public SchedulerDefineResp update(
      Long projectCode, Long scheduleId, SchedulerDefineParam schedulerDefineParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules" + scheduleId;

    try {
      HttpRestResult<SchedulerDefineResp> restResult =
          dolphinsRestTemplate.putForm(
              url, getHeader(), schedulerDefineParam, SchedulerDefineResp.class);
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
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("online dolphin scheduler schedule fail", e);
    }
  }

  /**
   * online schedule
   *
   * @param projectCode project code
   * @param scheduleId id
   * @return true for success,otherwise false
   */
  public Boolean offline(Long projectCode, Long scheduleId) {
    String url =
        dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId + "/offline";
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("offline dolphin scheduler schedule fail", e);
    }
  }

  /**
   * online schedule
   *
   * @param projectCode project code
   * @param scheduleId id
   * @return true for success,otherwise false
   */
  public Boolean delete(Long projectCode, Long scheduleId) {
    String url = dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleId;
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler schedule fail", e);
    }
  }
}
