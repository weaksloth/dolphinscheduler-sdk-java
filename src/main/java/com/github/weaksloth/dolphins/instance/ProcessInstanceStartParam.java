package com.github.weaksloth.dolphins.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/** dolphin运行工作流实例参数 */
@Data
@Accessors(chain = true)
public class ProcessInstanceStartParam {

  /** 失败策略 继续或者是结束 */
  private String failureStrategy;

  /** 工作流编号 */
  private String processDefinitionCode;

  /** 流程优先级 */
  private String processInstancePriority;

  /** 调度时间 */
  private String scheduleTime;

  /** 告警组ID */
  private String warningGroupId;

  /** 通知策略 */
  private String warningType;

  /** 是否空跑 */
  private String dryRun;

  /** 运行环境ID */
  private String environmentCode;

  private String execType;

  /** 期望并行度 */
  private String expectedParallelismNumber;

  /** run mode,可用值:RUN_MODE_SERIAL,RUN_MODE_PARALLEL */
  private String runMode;

  private String startNodeList;

  private String startParams;

  private String taskDependType;

  /** worker分组 */
  private String workerGroup;
}
