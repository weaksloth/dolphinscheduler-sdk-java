package com.github.weaksloth.dolphins.util;

import com.github.weaksloth.dolphins.process.TaskDefinition;
import com.github.weaksloth.dolphins.task.AbstractTask;

public class TaskDefinitionUtils {

  public static final String FLAG_YES = "YES"; // the node will be executed
  public static final String FLAG_NO = "NO"; // the node will not be executed

  public static final String PRIORITY_HIGHEST = "HIGHEST";
  public static final String PRIORITY_HIGH = "HIGH";
  public static final String PRIORITY_MEDIUM = "MEDIUM";
  public static final String PRIORITY_LOW = "LOW";
  public static final String PRIORITY_LOWEST = "LOWEST";

  /**
   * create task definition with default config {@link TaskDefinition} which can satisfy basic needs
   *
   * @param taskCode task node's code,generate by api
   * @param task {@link AbstractTask}
   */
  public static TaskDefinition createDefaultTaskDefinition(Long taskCode, AbstractTask task) {
    return createTaskDefinition(taskCode, 0, task, FLAG_YES, PRIORITY_MEDIUM);
  }

  /**
   * create task definition with default config {@link TaskDefinition} which can satisfy basic needs
   *
   * @param taskCode task node's code,generate by api
   * @param version task node's version
   * @param task {@link AbstractTask}
   */
  public static TaskDefinition createDefaultTaskDefinition(
      Long taskCode, Integer version, AbstractTask task) {
    return createTaskDefinition(taskCode, version, task, FLAG_YES, PRIORITY_MEDIUM);
  }

  /**
   * create task which wll not be executed
   *
   * @param taskCode task node's code,generate by api
   * @param task {@link AbstractTask}
   */
  public static TaskDefinition createBannedTaskDefinition(
      Long taskCode, Integer version, AbstractTask task) {
    return createTaskDefinition(taskCode, version, task, FLAG_NO, PRIORITY_MEDIUM);
  }

  /**
   * create high priority task
   *
   * @param taskCode task node's code,generate by api
   * @param task {@link AbstractTask}
   */
  public static TaskDefinition createHighLevelTaskDefinition(
      Long taskCode, Integer version, AbstractTask task) {
    return createTaskDefinition(taskCode, version, task, FLAG_YES, PRIORITY_HIGH);
  }

  /**
   * create task definition
   *
   * @param taskCode task node's code,generate by api
   * @param version task node's version
   * @param task {@link AbstractTask}
   * @param flag YES or NO
   * @param taskPriority {@link #PRIORITY_HIGH,#PRIORITY_HIGHEST}...
   */
  public static TaskDefinition createTaskDefinition(
      Long taskCode, Integer version, AbstractTask task, String flag, String taskPriority) {
    TaskDefinition taskDefinition = new TaskDefinition();
    String taskName = task.getTaskType().concat(String.valueOf(System.currentTimeMillis()));
    taskDefinition
        .setCode(taskCode)
        .setVersion(version)
        .setName(taskName)
        .setDescription("")
        .setTaskType(task.getTaskType())
        .setTaskParams(task)
        .setFlag(flag)
        .setTaskPriority(taskPriority)
        .setWorkerGroup("default")
        .setFailRetryTimes("0")
        .setFailRetryInterval("1")
        .setTimeoutFlag("CLOSE")
        .setTimeoutNotifyStrategy("WARN");
    return taskDefinition;
  }
}
