package com.github.weaksloth.dolphins.util;

import com.github.weaksloth.dolphins.process.TaskDefinition;
import com.github.weaksloth.dolphins.task.AbstractTask;

public class TaskDefinitionUtils {

  /**
   * create task definition with default config {@link TaskDefinition} which can satisfy basic needs
   *
   * @param taskCode task node's code,you can generate by api
   * @param task {@link AbstractTask}
   * @return
   */
  public static TaskDefinition createDefaultTaskDefinition(Long taskCode, AbstractTask task) {
    TaskDefinition taskDefinition = new TaskDefinition();
    String taskName = task.getTaskType().concat(String.valueOf(System.currentTimeMillis()));
    taskDefinition
        .setCode(taskCode)
        .setVersion(0)
        .setName(taskName)
        .setDescription("")
        .setTaskType(task.getTaskType())
        .setTaskParams(task)
        .setFlag("YES")
        .setTaskPriority("MEDIUM")
        .setWorkerGroup("default")
        .setFailRetryTimes("0")
        .setFailRetryInterval("1")
        .setTimeoutFlag("CLOSE")
        .setTimeoutNotifyStrategy("WARN");
    return taskDefinition;
  }
}
