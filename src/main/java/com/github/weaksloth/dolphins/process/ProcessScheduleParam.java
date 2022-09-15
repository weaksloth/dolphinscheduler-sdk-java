package com.github.weaksloth.dolphins.process;

import lombok.Data;
import lombok.experimental.Accessors;

/** 定时调度工作流参数 */
@Data
@Accessors(chain = true)
public class ProcessScheduleParam {

  private String schedule;

  /** 失败策略，默认失败了就结束 */
  private String failureStrategy = "END";

  /** 告警信息 */
  private String warningType = "NONE";

  /** 优先级，默认medium */
  private String processInstancePriority = "MEDIUM";

  /** 告警组ID */
  private String warningGroupId = "0";

  /** 工作组 */
  private String workerGroup = "default";

  private String environmentCode = "";

  /** 工作流ID */
  private String processDefinitionCode;

  @Data
  @Accessors(chain = true)
  public static class Schedule {
    private String startTime;
    private String endTime;
    private String crontab;
    private String timezoneId = "Asia/Shanghai";
  }
}
