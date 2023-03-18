package com.github.weaksloth.dolphins.workflow;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.process.*;
import com.github.weaksloth.dolphins.task.ShellTask;
import com.github.weaksloth.dolphins.util.TaskDefinitionUtils;
import com.github.weaksloth.dolphins.util.TaskLocationUtils;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

/** the test for workflow/process */
public class WorkflowTest extends BaseTest {

  /**
   * 1.generate task code
   *
   * <p>2.create tasks
   *
   * <p>3.create task definitions
   *
   * <p>4.create task relations
   *
   * <p>5.create process create parm
   *
   * <p>
   */
  @Test
  public void testCreateProcessDefinition() {

    Long taskCode = getClient().opsForProcess().generateTaskCode(projectCode, 1).get(0);
    ShellTask shellTask = new ShellTask();
    shellTask.setRawScript("echo 'hello dolphin scheduler java sdk'");

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, shellTask);

    // only one task,so only need to set post task code
    TaskRelation taskRelation = new TaskRelation().setPostTaskCode(taskCode);

    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName("test-dag")
        .setLocations(TaskLocationUtils.verticalLocation(taskCode))
        .setDescription("test-dag-description")
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setTaskDefinitionJson(Collections.singletonList(taskDefinition))
        .setTaskRelationJson(Collections.singletonList(taskRelation))
        .setGlobalParams(null);

    System.out.println(getClient().opsForProcess().create(projectCode, pcr));
  }

  @Test
  public void onlineWorkflow() {
    Long workflowCode = 6919932328128L;
    Assert.assertTrue(getClient().opsForProcess().online(projectCode, workflowCode));
  }

  @Test
  public void offlineWorkflow() {
    Long workflowCode = 6919932328128L;
    Assert.assertTrue(getClient().opsForProcess().offline(projectCode, workflowCode));
  }

  /** the workflow must in offline state */
  @Test
  public void deleteWorkflow() {
    Long workflowCode = 6919932328128L;
    Assert.assertTrue(getClient().opsForProcess().delete(projectCode, workflowCode));
  }
}
