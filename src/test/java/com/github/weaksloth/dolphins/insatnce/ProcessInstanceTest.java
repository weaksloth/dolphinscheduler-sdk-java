package com.github.weaksloth.dolphins.insatnce;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.instance.ProcessInstanceCreateParam;
import org.junit.Assert;
import org.junit.Test;

public class ProcessInstanceTest extends BaseTest {

  public static final Long WORKFLOW_CODE = 9488158676288L;

  /** the workflow must in online state,otherwise will cause error */
  @Test
  public void testStartInstance() {

    ProcessInstanceCreateParam startParam = new ProcessInstanceCreateParam();
    startParam
        .setProcessDefinitionCode(WORKFLOW_CODE)
        .setScheduleTime("")
        .setFailureStrategy("CONTINUE")
        .setWarningType("NONE")
        .setWarningGroupId(0L)
        .setExecType("")
        .setStartNodeList("")
        .setTaskDependType("TASK_POST")
        .setRunMode("RUN_MODE_SERIAL")
        .setProcessInstancePriority("MEDIUM")
        .setWorkerGroup("default")
        .setEnvironmentCode("")
        .setStartParams("")
        .setExpectedParallelismNumber("")
        .setDryRun(0);
    Assert.assertTrue(getClient().opsForProcessInst().start(projectCode, startParam));
  }

  @Test
  public void testReRun() {
    Long instanceId = 1L;
    Assert.assertTrue(getClient().opsForProcessInst().reRun(projectCode, instanceId));
  }

  @Test
  public void testPage() {
    getClient()
        .opsForProcessInst()
        .page(null, null, projectCode, WORKFLOW_CODE)
        .forEach(System.out::println);
  }

  @Test
  public void testDelete() {
    Long instanceId = 1L;
    Assert.assertTrue(getClient().opsForProcessInst().delete(projectCode, instanceId));
  }
}
