package com.github.weaksloth.dolphins.core;

import com.github.weaksloth.dolphins.datasource.DataSourceOperator;
import com.github.weaksloth.dolphins.instance.ProcessInstanceOperator;
import com.github.weaksloth.dolphins.process.ProcessOperator;
import com.github.weaksloth.dolphins.project.ProjectOperator;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.resource.ResourceOperator;
import com.github.weaksloth.dolphins.schedule.ScheduleOperator;
import com.github.weaksloth.dolphins.tenant.TenantOperator;
import lombok.extern.slf4j.Slf4j;

/** dolphin scheduler client to operate dolphin scheduler */
@Slf4j
public class DolphinClient {

  private final DolphinsRestTemplate dolphinsRestTemplate;
  private final String dolphinAddress;
  private final String token;

  private DataSourceOperator dataSourceOperator;
  private ResourceOperator resourceOperator;
  private ProcessOperator processOperator;
  private ProcessInstanceOperator processInstanceOperator;
  private ScheduleOperator scheduleOperator;
  private ProjectOperator projectOperator;
  private TenantOperator tenantOperator;

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
    this.scheduleOperator =
        new ScheduleOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.projectOperator =
        new ProjectOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.tenantOperator =
        new TenantOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
  }

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

  public ScheduleOperator opsForSchedule() {
    return this.scheduleOperator;
  }

  public ProjectOperator opsForProject() {
    return this.projectOperator;
  }

  public TenantOperator opsForTenant() {
    return this.tenantOperator;
  }
}
