package com.github.weaksloth.dolphins.instance;

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
public class ProcessInstanceOperator extends AbstractOperator {

  public ProcessInstanceOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * start/run process instance
   *
   * <p>api: /dolphinscheduler/projects/{projectCode}/executors/start-process-instance
   *
   * @param processInstanceCreateParam process instance create param
   * @return true for success,otherwise false
   */
  public Boolean start(Long projectCode, ProcessInstanceCreateParam processInstanceCreateParam) {
    String url = dolphinAddress + "/projects/" + projectCode + "/executors/start-process-instance";
    log.info("start process instance ,url:{}", url);
    try {
      HttpRestResult<JsonNode> restResult =
          dolphinsRestTemplate.postForm(
              url, getHeader(), processInstanceCreateParam, JsonNode.class);
      log.info("start process response:{}", restResult);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("start dolphin scheduler process instance fail", e);
    }
  }

  /**
   * page query process's instance list
   *
   * @param page page,default 1 while is null
   * @param size size,default 10 while is null
   * @param projectCode project code
   * @param workflowCode workflow id
   * @return
   */
  public List<ProcessInstanceQueryResp> page(
      Integer page, Integer size, Long projectCode, Long workflowCode) {
    page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
    size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

    String url = dolphinAddress + "/projects/" + projectCode + "/process-instances";

    Query query = new Query();
    query
        .addParam("pageNo", String.valueOf(page))
        .addParam("pageSize", String.valueOf(size))
        .addParam("processDefineCode", String.valueOf(workflowCode));

    try {
      HttpRestResult<JsonNode> restResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);
      return JacksonUtils.parseObject(
              restResult.getData().toString(),
              new TypeReference<PageInfo<ProcessInstanceQueryResp>>() {})
          .getTotalList();
    } catch (Exception e) {
      throw new DolphinException("page dolphin scheduler process instance list fail", e);
    }
  }

  /**
   * repeat run dolphin scheduler workflow instance
   *
   * @param projectCode project code
   * @param processInstanceId process instance id
   * @return true for success,otherwise false
   */
  public Boolean reRun(Long projectCode, Long processInstanceId) {
    log.info("repeat run workflow instance,id:{}", processInstanceId);
    String url = dolphinAddress + "/projects/" + projectCode + "/executors/execute";
    ProcessInstanceRunParam reProcessInstanceRunParam =
        new ProcessInstanceRunParam()
            .setProcessInstanceId(processInstanceId)
            .setExecuteType(DolphinClientConstant.RE_RUN_EXECUTE_TYPE);
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), reProcessInstanceRunParam, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("repeat run dolphin scheduler process instance fail", e);
    }
  }

  public Boolean delete(Long projectCode, Long processInstanceId) {
    String url =
        dolphinAddress + "/projects/" + projectCode + "/process-instances/" + processInstanceId;
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler process instance fail", e);
    }
  }
}
