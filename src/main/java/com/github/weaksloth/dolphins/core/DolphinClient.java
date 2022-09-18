package com.github.weaksloth.dolphins.core;

import com.github.weaksloth.dolphins.datasource.DataSourceOperator;
import com.github.weaksloth.dolphins.instance.ProcessInstanceOperator;
import com.github.weaksloth.dolphins.process.ProcessOperator;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.Header;
import com.github.weaksloth.dolphins.resource.ResourceOperator;
import lombok.extern.slf4j.Slf4j;

/** dolphin scheduler client to operate dolphin scheduler */
@Slf4j
public class DolphinClient {

  private DolphinsRestTemplate dolphinsRestTemplate;
  private String dolphinAddress;
  private String token;

  private DataSourceOperator dataSourceOperator;
  private ResourceOperator resourceOperator;
  private ProcessOperator processOperator;
  private ProcessInstanceOperator processInstanceOperator;

  public DolphinClient(
      String token, String dolphinAddress, DolphinsRestTemplate dolphinsRestTemplate) {
    this.token = token;
    this.dolphinAddress = dolphinAddress;
    this.dolphinsRestTemplate = dolphinsRestTemplate;
    this.initOperators();
  }

  public void initOperators() {
    this.dataSourceOperator =
        new DataSourceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.resourceOperator =
        new ResourceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.processOperator =
        new ProcessOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.processInstanceOperator =
        new ProcessInstanceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
  }

  //
  //  /**
  //   * 定时工作流
  //   *
  //   * @param processScheduleParam 定时工作流参数
  //   * @return schedule response
  //   */
  //  public JsonNode scheduleProcess(ProcessScheduleParam processScheduleParam) {
  //    String url = dolphinAddress + "/projects/" + projectCode + "/schedules";
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, processScheduleParam);
  //    if (response.getSuccess()) {
  //      return response.getData();
  //    } else {
  //      log.error("定义dolphin scheduler定时失败，response:{}", response);
  //      throw new DolphinException("定义dolphin scheduler定时失败");
  //    }
  //  }
  //
  //  /**
  //   * 获取工作流实例信息
  //   *
  //   * @param workflowId 工作流ID
  //   * @return 工作流ID对应的实例数组
  //   */
  //  public JsonNode getProcessInstance(Long workflowId) {
  //    String url =
  //        dolphinAddress
  //            + "/projects/"
  //            + projectCode
  //            +
  // "/process-instances?pageNo={pageNo}&pageSize={pageSize}&processDefineCode={processDefineCode}";
  //
  //    DolphinResponse response = httpSender.sendGetRequest(url, 1, 10, workflowId);
  //    if (response.getSuccess()) {
  //      return response.getData();
  //    } else {
  //      log.error("获取dolphin scheduler工作流实例信息失败,response:{}", response);
  //      throw new DolphinException("获取dolphin scheduler工作流实例信息失败");
  //    }
  //  }
  //
  //  /**
  //   * 重跑工作流对应的最新的实例
  //   *
  //   * @param workflowId 工作流ID
  //   * @return 重跑响应
  //   */
  //  public Boolean reRunProcessInstance(Long workflowId) {
  //    String processInstanceId =
  //        JSONUtils.getAsArrayNode(this.getProcessInstance(workflowId), "totalList")
  //            .get(0)
  //            .get("id")
  //            .asText();
  //    log.info("re run workflow instance,id:{}", processInstanceId);
  //    String url = dolphinAddress + "/projects/" + projectCode + "/executors/execute";
  //    ProcessInstanceRunParam reProcessInstanceRunParam =
  //        new ProcessInstanceRunParam()
  //            .setProcessInstanceId(processInstanceId)
  //            .setExecuteType(DolphinClientConstant.RE_RUN_EXECUTE_TYPE);
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, reProcessInstanceRunParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("重跑dolphin scheduler工作流实例失败，response:{}", response);
  //      throw new DolphinException("重跑dolphin scheduler工作流实例失败");
  //    }
  //  }
  //
  //  /**
  //   * 上线定时工作流
  //   *
  //   * @param scheduleTaskId 定时工作流id
  //   * @return
  //   */
  //  public Boolean onlineScheduleTask(Long scheduleTaskId) {
  //    String url =
  //        dolphinAddress + "/projects/" + projectCode + "/schedules/" + scheduleTaskId +
  // "/online";
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, null);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("上线dolphin scheduler定时失败，response:{}", response);
  //      throw new DolphinException("上线dolphin scheduler定时失败");
  //    }
  //  }
  //

  public DataSourceOperator opsForDataSource() {
    return this.dataSourceOperator;
  }

  public ResourceOperator opsForResource() {
    return this.resourceOperator;
  }

  public ProcessOperator opsForProcess() {
    return this.processOperator;
  }

  public ProcessInstanceOperator opsForProcessInst() {
    return this.processInstanceOperator;
  }

  /**
   * get header for dolphin scheduler
   *
   * @return
   */
  private Header getHeader() {
    Header header = Header.newInstance();
    header.addParam("token", this.token);
    return header;
  }
}
