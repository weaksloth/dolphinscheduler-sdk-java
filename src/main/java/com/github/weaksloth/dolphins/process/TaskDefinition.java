package com.github.weaksloth.dolphins.process;

import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.task.AbstractTask;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskDefinition {

  private Long code;

  private Integer version;

  private String name;

  private String description;

  private String taskType;

  private AbstractTask taskParams; // 任务配置json字符串

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

  /**
   * must rewrite,then {@link RequestHttpEntity#bodyToMap()} can transfer object to json string
   *
   * @return object json string
   */
  @Override
  public String toString() {
    return JacksonUtils.toJSONString(this);
  }
}
