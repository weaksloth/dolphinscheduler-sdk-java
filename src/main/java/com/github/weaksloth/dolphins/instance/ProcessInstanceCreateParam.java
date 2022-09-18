package com.github.weaksloth.dolphins.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/** process instance create param */
@Data
@Accessors(chain = true)
public class ProcessInstanceCreateParam {

  /** continue or and */
  private String failureStrategy;

  private Long processDefinitionCode;

  private String processInstancePriority;

  private String scheduleTime;

  private Long warningGroupId;

  private String warningType;

  /** o or 1 */
  private Integer dryRun;

  /** env code */
  private String environmentCode;

  private String execType;

  private String expectedParallelismNumber;

  /** run mode,value:RUN_MODE_SERIAL,RUN_MODE_PARALLEL */
  private String runMode;

  private String startNodeList;

  private String startParams;

  private String taskDependType;

  /** worker group */
  private String workerGroup;
}
