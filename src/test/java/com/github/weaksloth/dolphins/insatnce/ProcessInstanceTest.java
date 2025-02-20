package com.github.weaksloth.dolphins.insatnce;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.enums.*;
import com.github.weaksloth.dolphins.instance.ProcessInstanceCreateParam;
import org.junit.Assert;
import org.junit.Test;

public class ProcessInstanceTest extends BaseTest {

  public static final Long PROCESS_DEFINITION_CODE = 11386905142912L;

  /** the workflow must in online state,otherwise will cause error */
  @Test
  public void testStartInstance() {

    ProcessInstanceCreateParam startParam = new ProcessInstanceCreateParam();
    startParam
        .setProcessDefinitionCode(PROCESS_DEFINITION_CODE)
        .setScheduleTime("")
        .setFailureStrategy(FailureStrategy.CONTINUE.toString())
        .setWarningType(WarningType.NONE.toString())
        .setWarningGroupId(0L)
        .setExecType("")
        .setStartNodeList("")
        .setTaskDependType(TaskDependType.TASK_POST.toString())
        .setRunMode(RunMode.RUN_MODE_SERIAL.toString())
        .setProcessInstancePriority(Priority.MEDIUM.toString())
        .setWorkerGroup("default")
        .setEnvironmentCode("")
        .setStartParams("")
        .setExpectedParallelismNumber("")
        .setDryRun(0);
    Assert.assertTrue(getClient().opsForProcessInst().start(projectCode, startParam));
  }

  @Test
  public void testReRun() {
    Long instanceId = 31L;
    Assert.assertTrue(getClient().opsForProcessInst().reRun(projectCode, instanceId));
  }

  @Test
  public void testPage() {
    getClient()
        .opsForProcessInst()
        .page(null, null, projectCode, PROCESS_DEFINITION_CODE)
        .forEach(System.out::println);
  }

  @Test
  public void testDelete() {
    Long instanceId = 31L;
    Assert.assertTrue(getClient().opsForProcessInst().delete(projectCode, instanceId));
  }
}
