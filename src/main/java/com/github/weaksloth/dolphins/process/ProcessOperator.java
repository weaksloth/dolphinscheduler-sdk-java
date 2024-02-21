package com.github.weaksloth.dolphins.process;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.Query;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessOperator extends AbstractOperator {

  public ProcessOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * page query process define(workflow)
   *
   * @param projectCode project code
   * @param page page
   * @param size size
   * @param searchVal process name
   * @return list
   */
  public List<ProcessDefineResp> page(
      Long projectCode, Integer page, Integer size, String searchVal) {
    page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
    size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);
    searchVal = Optional.ofNullable(searchVal).orElse("");

    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition";
    Query query =
        new Query()
            .addParam("pageNo", String.valueOf(page))
            .addParam("pageSize", String.valueOf(size))
            .addParam("searchVal", searchVal);

    try {
      HttpRestResult<JsonNode> restResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

      return JacksonUtils.parseObject(
              restResult.getData().toString(), new TypeReference<PageInfo<ProcessDefineResp>>() {})
          .getTotalList();
    } catch (Exception e) {
      throw new DolphinException("list dolphin scheduler workflow fail", e);
    }
  }

  /**
   * create dolphin scheduler process api:
   * /dolphinscheduler/projects/{projectCode}/process-definition
   *
   * @param projectCode project code
   * @param processDefineParam create process param
   * @return create response
   */
  public ProcessDefineResp create(Long projectCode, ProcessDefineParam processDefineParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition";
    log.info(
        "create process definition, url:{}, param:{}",
        url,
        JacksonUtils.toJSONString(processDefineParam));
    try {
      HttpRestResult<ProcessDefineResp> restResult =
          dolphinsRestTemplate.postForm(
              url, getHeader(), processDefineParam, ProcessDefineResp.class);
      if (restResult.getSuccess()) {
        return restResult.getData();
      } else {
        log.error("dolphin scheduler response:{}", restResult);
        throw new DolphinException("create dolphin scheduler workflow fail");
      }
    } catch (Exception e) {
      throw new DolphinException("create dolphin scheduler workflow fail", e);
    }
  }

  /**
   * update dolphin scheduler workflow
   *
   * <p>api:/dolphinscheduler/projects/{projectCode}/process-definition/{process-definition-code}
   *
   * @param processDefineParam update process def param
   * @param processCode workflow code
   * @return update response json
   */
  public ProcessDefineResp update(
      Long projectCode, ProcessDefineParam processDefineParam, Long processCode) {
    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition/" + processCode;
    log.info("update process definition, url:{}, param:{}", url, processDefineParam);
    try {
      HttpRestResult<ProcessDefineResp> restResult =
          dolphinsRestTemplate.putForm(
              url, getHeader(), processDefineParam, ProcessDefineResp.class);
      if (restResult.getSuccess()) {
        return restResult.getData();
      } else {
        log.error("dolphin scheduler response:{}", restResult);
        throw new DolphinException("update dolphin scheduler workflow fail");
      }
    } catch (Exception e) {
      throw new DolphinException("update dolphin scheduler workflow fail", e);
    }
  }

  /**
   * delete process
   *
   * @param projectCode project code
   * @param processCode process code
   * @return true for success,otherwise false
   */
  public Boolean delete(Long projectCode, Long processCode) {
    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition/" + processCode;
    log.info("delete process definition,processCode:{}, url:{}", processCode, url);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler workflow fail", e);
    }
  }

  /**
   * release, api: /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release
   *
   * @param projectCode project code
   * @param code workflow id
   * @param processReleaseParam param
   * @return true for success,otherwise false
   */
  public Boolean release(Long projectCode, Long code, ProcessReleaseParam processReleaseParam) {
    String url =
        dolphinAddress + "/projects/" + projectCode + "/process-definition/" + code + "/release";
    log.info("release process definition,url:{}, param:{}", url, processReleaseParam);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), processReleaseParam, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("release dolphin scheduler workflow fail", e);
    }
  }

  /**
   * online workflow, this method can replace {@link #release(Long, Long, ProcessReleaseParam)}
   *
   * @param projectCode project code
   * @param code workflow id
   * @return true for success,otherwise false
   */
  public Boolean online(Long projectCode, Long code) {
    return release(projectCode, code, ProcessReleaseParam.newOnlineInstance());
  }

  /**
   * offline workflow, this method can replace {@link #release(Long, Long, ProcessReleaseParam)}
   *
   * @param projectCode project code
   * @param code workflow id
   * @return true for success,otherwise false
   */
  public Boolean offline(Long projectCode, Long code) {
    return release(projectCode, code, ProcessReleaseParam.newOfflineInstance());
  }

  /**
   * generate task code
   *
   * @param projectCode project's code
   * @param codeNumber the number of task code
   * @return task code list
   */
  public List<Long> generateTaskCode(Long projectCode, int codeNumber) {
    String url = dolphinAddress + "/projects/" + projectCode + "/task-definition/gen-task-codes";
    Query query = new Query();
    query.addParam("genNum", String.valueOf(codeNumber));
    try {
      HttpRestResult<List> restResult =
          dolphinsRestTemplate.get(url, getHeader(), query, List.class);
      return (List<Long>) restResult.getData();
    } catch (Exception e) {
      throw new DolphinException("generate task code fail", e);
    }
  }
}
