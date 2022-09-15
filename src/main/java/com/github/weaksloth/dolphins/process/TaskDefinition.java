package com.github.weaksloth.dolphins.process;

import lombok.Data;

@Data
public class TaskDefinition {

  private Long code;

  private Integer version;

  private String name;

  private String description;

  private String taskType;

  private Object taskParams; // 任务配置json字符串

  private String flag;

  private String taskPriority;

  private String workerGroup;

  private String failRetryTimes;

  private String failRetryInterval;

  private String timeoutFlag;

  private String timeoutNotifyStrategy;

  private Integer timeout = 0;

  private String delayTime = "0";

  private Integer environmentCode = -1;
}
