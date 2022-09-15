package com.github.weaksloth.dolphins.core;

import lombok.extern.slf4j.Slf4j;

/** dolphin scheduler rest api client to operate dolphin scheduler */
@Slf4j
public class DolphinClient {

  //  private DolphinHttpSender httpSender;
  //
  //  private String dolphinAddress;
  //
  //  private String projectCode;
  //
  //  public DolphinClient(String dolphinAddress,String projectCode) {
  //    this.dolphinAddress = dolphinAddress;
  //    this.projectCode = projectCode;
  //  }
  //
  //  /**
  //   * 创建dolphin工作流 api: /dolphinscheduler/projects/{projectCode}/process-definition
  //   *
  //   * @param createProcessDefParam 创建dolphin工作流的参数
  //   * @return create response
  //   */
  //  public JsonNode createProcessDefinition(ProcessDefinition createProcessDefParam) {
  //    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition";
  //    log.info("create process definition,url:{}", url);
  //    DolphinResponse dolphinResponse =
  //        httpSender.sendPostForm(url, createProcessDefParam);
  //    if (dolphinResponse.getSuccess()) {
  //      return dolphinResponse.getData();
  //    } else {
  //      log.error("创建dolphin scheduler工作流失败,response:{}", dolphinResponse);
  //      throw new DolphinException("创建dolphin scheduler工作流失败");
  //    }
  //  }
  //
  //  /**
  //   * 更新dolphin工作流
  //   * api:/dolphinscheduler/projects/{projectCode}/process-definition/{process-definition-code}
  //   *
  //   * @param createProcessDefParam dolphin工作流参数
  //   * @param processCode 工作流code
  //   * @return update response json
  //   */
  //  public Boolean updateProcessDefinition(
  //          ProcessDefinition createProcessDefParam, Long processCode) {
  //    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition/" +
  // processCode;
  //    log.info("update process definition,url:{}", url);
  //    DolphinResponse response = httpSender.sendPutFormRequest(url, createProcessDefParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("更新dolphin scheduler 工作流失败，response:{}", response);
  //      throw new DolphinException("更新dolphin scheduler工作流失败");
  //    }
  //  }
  //
  //  /**
  //   * 删除工作流
  //   *
  //   * @param processCode 流程编号
  //   * @return delete response
  //   */
  //  public Boolean deleteProcessDefinition(Long processCode) {
  //    String url = dolphinAddress + "/projects/" + projectCode + "/process-definition/" +
  // processCode;
  //    DolphinResponse response = httpSender.sendDeleteRequest(url, null);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("删除dolphin scheduler 工作流失败，response:{}", response);
  //      throw new DolphinException("删除dolphin scheduler工作流失败");
  //    }
  //  }
  //
  //  /**
  //   * 上下线工作流 api: /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release
  //   *
  //   * @param code 工作流ID
  //   * @param releaseProcessDefParam 上线工作流参数
  //   * @return release response
  //   */
  //  public Boolean releaseProcess(Long code, ReleaseProcessDefParam releaseProcessDefParam) {
  //    String url =
  //        dolphinAddress + "/projects/" + projectCode + "/process-definition/" + code +
  // "/release";
  //    log.info("release process definition,url:{}", url);
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, releaseProcessDefParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("上下线dolphin scheduler工作流失败，response:{}", response);
  //      throw new DolphinException("上下线dolphin scheduler工作流失败");
  //    }
  //  }
  //
  //  /**
  //   * 运行工作流 api: /dolphinscheduler/projects/{projectCode}/executors/start-process-instance
  //   *
  //   * @param processInstanceStartParam 运行实例参数
  //   * @return start response
  //   */
  //  public Boolean startProcessInstance(ProcessInstanceStartParam processInstanceStartParam) {
  //    String url = dolphinAddress + "/projects/" + projectCode +
  // "/executors/start-process-instance";
  //    log.info("start process instance ,url:{}", url);
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, processInstanceStartParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("运行dolphin scheduler工作流失败，response:{}", response);
  //      throw new DolphinException("运行dolphin scheduler工作流失败");
  //    }
  //  }
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
  //  /**
  //   * 创建文件
  //   *
  //   * @param fileCreateParam 创建文件参数
  //   * @return 成功返回true，反之返回false
  //   */
  //  public Boolean onlineCreateFile(FileCreateParam fileCreateParam) {
  //    String url = dolphinAddress + "/resources/online-create";
  //    DolphinResponse response = httpSender.sendPostFormRequest(url, fileCreateParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("创建dolphin scheduler文件失败，response:{}", response);
  //      throw new DolphinException("创建dolphin scheduler文件失败");
  //    }
  //  }
  //
  //  /**
  //   * 更新文件内容
  //   *
  //   * @param fileUpdateParam 更新文件表单
  //   * @return 更新成功返回true，反之返回false
  //   */
  //  public Boolean onlineUpdateFileContent(FileUpdateParam fileUpdateParam) {
  //    String url = dolphinAddress + "/resources/" + fileUpdateParam.getId() + "/update-content";
  //    DolphinResponse response = httpSender.sendPutFormRequest(url, fileUpdateParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("更新dolphin scheduler文件内容失败，response:{}", response);
  //      throw new DolphinException("更新dolphin scheduler文件内容失败");
  //    }
  //  }
  //
  //  /**
  //   * 查询文件列表
  //   *
  //   * @param pid pid
  //   * @param fileName 文件名
  //   * @return json str
  //   */
  //  public JsonNode listFile(String pid, String fileName) {
  //    String url =
  //        dolphinAddress
  //            +
  // "/resources?type=FILE&pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&id={id}";
  //    DolphinResponse response = httpSender.sendGetRequest(url, 1, 10, fileName, pid);
  //    if (response.getSuccess()) {
  //      return response.getData();
  //    } else {
  //      log.error("查询dolphin scheduler文件列表失败，response:{}", response);
  //      throw new DolphinException("查询dolphin scheduler文件列表失败");
  //    }
  //  }
  //
  //  /**
  //   * 调用dolphin rest api 生成task code
  //   *
  //   * @param codeNumber
  //   * @return
  //   */
  //  public List<Long> generateTaskCode(int codeNumber) {
  //    List<Long> list = new ArrayList<>(codeNumber);
  //    String url =
  //        dolphinAddress
  //            + "/projects/"
  //            + projectCode
  //            + "/task-definition/gen-task-codes?genNum="
  //            + codeNumber;
  //    DolphinResponse response = httpSender.sendGetRequest(url);
  //    ArrayNode dataArray = (ArrayNode) response.getData();
  //    for (JsonNode node : dataArray) {
  //      list.add(node.asLong());
  //    }
  //    return list;
  //  }
  //
  //  /**
  //   * 创建数据源 api:/dolphinscheduler/datasources
  //   *
  //   * @param dataSourceCreateParam 创建数据源参数
  //   * @return
  //   */
  //  public Boolean createDataSource(DataSourceCreateParam dataSourceCreateParam) {
  //    String url = dolphinAddress + "/datasources";
  //    DolphinResponse response = httpSender.sendPostJsonRequest(url, dataSourceCreateParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("create dolphin scheduler datasource fail，response:{}", response);
  //      throw new DolphinException("create dolphin scheduler datasource fail");
  //    }
  //  }
  //
  //  /**
  //   * 更新数据源 api：/dolphinscheduler/datasources/{id}
  //   *
  //   * @param dataSourceUpdateParam 更新数据源参数
  //   * @return
  //   */
  //  public Boolean updateDataSource(DataSourceUpdateParam dataSourceUpdateParam) {
  //    String url = dolphinAddress + "/datasources/" + dataSourceUpdateParam.getId();
  //    DolphinResponse response = httpSender.sendPutJsonRequest(url, dataSourceUpdateParam);
  //    if (response.getSuccess()) {
  //      return true;
  //    } else {
  //      log.error("更新dolphin scheduler数据源失败，response:{}", response);
  //      throw new DolphinException("更新dolphin scheduler数据源失败");
  //    }
  //  }
  //
  //  /**
  //   * 查询dolphin datasource列表，api:/dolphinscheduler/datasources
  //   *
  //   * @return list dolphin response json
  //   */
  //  public JsonNode listDolphinDataSource(String dsName) {
  //    String url =
  //        dolphinAddress +
  // "/datasources?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}";
  //    log.info("query dolphin datasource");
  //    DolphinResponse response = httpSender.sendGetRequest(url, 1, 100, dsName);
  //    if (response.getSuccess()) {
  //      return response.getData();
  //    } else {
  //      log.error("查询dolphin scheduler数据源失败，response:{}", response);
  //      throw new DolphinException("查询dolphin scheduler数据源失败");
  //    }
  //  }
}
