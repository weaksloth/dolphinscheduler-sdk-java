package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public enum WorkflowExecutionStatus {

  // This class is split from <code>ExecutionStatus</code> #11339.
  // In order to compatible with the old value, the code is not consecutive
  SUBMITTED_SUCCESS(0, "submit success"),
  RUNNING_EXECUTION(1, "running"),
  READY_PAUSE(2, "ready pause"),
  PAUSE(3, "pause"),
  READY_STOP(4, "ready stop"),
  STOP(5, "stop"),
  FAILURE(6, "failure"),
  SUCCESS(7, "success"),
  DELAY_EXECUTION(12, "delay execution"),
  SERIAL_WAIT(14, "serial wait"),
  READY_BLOCK(15, "ready block"),
  BLOCK(16, "block"),
  WAIT_TO_RUN(17, "wait to run"),
  ;

  private static final Map<Integer, WorkflowExecutionStatus> CODE_MAP = new HashMap<>();
  private static final int[] NEED_FAILOVER_STATES =
      new int[] {
        SUBMITTED_SUCCESS.getCode(),
        RUNNING_EXECUTION.getCode(),
        DELAY_EXECUTION.getCode(),
        READY_PAUSE.getCode(),
        READY_STOP.getCode()
      };

  static {
    for (WorkflowExecutionStatus executionStatus : WorkflowExecutionStatus.values()) {
      CODE_MAP.put(executionStatus.getCode(), executionStatus);
    }
  }

  /**
   * Get <code>WorkflowExecutionStatus</code> by code, if the code is invalidated will throw {@link
   * IllegalArgumentException}.
   */
  public static @NonNull WorkflowExecutionStatus of(int code) {
    WorkflowExecutionStatus workflowExecutionStatus = CODE_MAP.get(code);
    if (workflowExecutionStatus == null) {
      throw new IllegalArgumentException(
          String.format("The workflow execution status code: %s is invalidated", code));
    }
    return workflowExecutionStatus;
  }

  public boolean isRunning() {
    return this == RUNNING_EXECUTION;
  }

  public boolean canStop() {
    return this == RUNNING_EXECUTION || this == READY_PAUSE;
  }

  public boolean isFinished() {
    // todo: do we need to remove pause/block in finished judge?
    return isSuccess() || isFailure() || isStop() || isPause() || isBlock();
  }

  /**
   * status is success
   *
   * @return status
   */
  public boolean isSuccess() {
    return this == SUCCESS;
  }

  public boolean isFailure() {
    return this == FAILURE;
  }

  public boolean isPause() {
    return this == PAUSE;
  }

  public boolean isReadyStop() {
    return this == READY_STOP;
  }

  public boolean isStop() {
    return this == STOP;
  }

  public boolean isBlock() {
    return this == BLOCK;
  }

  public static int[] getNeedFailoverWorkflowInstanceState() {
    return NEED_FAILOVER_STATES;
  }

  private final int code;

  private final String desc;

  WorkflowExecutionStatus(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  @Override
  public String toString() {
    return "WorkflowExecutionStatus{" + "code=" + code + ", desc='" + desc + '\'' + '}';
  }
}
