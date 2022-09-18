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
  //
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
