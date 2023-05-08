package com.github.weaksloth.dolphins.workflow;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.process.*;
import com.github.weaksloth.dolphins.task.HttpTask;
import com.github.weaksloth.dolphins.task.ShellTask;
import com.github.weaksloth.dolphins.util.TaskDefinitionUtils;
import com.github.weaksloth.dolphins.util.TaskLocationUtils;
import com.github.weaksloth.dolphins.util.TaskRelationUtils;
import com.github.weaksloth.dolphins.util.TaskUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/** the test for workflow/process */
public class WorkflowTest extends BaseTest {

  public static final String WORKFLOW_NAME = "test-dag";

  /**
   * create simple workflow like: shellTask -> httpTask
   *
   * <p>1.generate task code
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

    List<Long> taskCodes = getClient().opsForProcess().generateTaskCode(projectCode, 2);

    // build shell task
    ShellTask shellTask = new ShellTask();
    shellTask.setRawScript("echo 'hello dolphin scheduler java sdk'");
    TaskDefinition shellTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCodes.get(0), shellTask);

    // build http task
    HttpTask httpTask = new HttpTask();
    httpTask
        .setUrl("http://www.baidu.com")
        .setHttpMethod("GET")
        .setHttpCheckCondition("STATUS_CODE_DEFAULT")
        .setConditionResult(TaskUtils.createEmptyConditionResult());
    TaskDefinition httpTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCodes.get(1), httpTask);

    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName(WORKFLOW_NAME)
        .setLocations(TaskLocationUtils.horizontalLocation(taskCodes.toArray(new Long[0])))
        .setDescription("test-dag-description")
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setTaskDefinitionJson(Arrays.asList(shellTaskDefinition, httpTaskDefinition))
        .setTaskRelationJson(TaskRelationUtils.oneLineRelation(taskCodes.toArray(new Long[0])))
        .setGlobalParams(null);

    System.out.println(getClient().opsForProcess().create(projectCode, pcr));
  }

  @Test
  public void testPage() {
    List<ProcessDefineResp> page =
        getClient().opsForProcess().page(projectCode, null, null, WORKFLOW_NAME);
    int expectedWorkflowNumber = 1;
    Assert.assertEquals(expectedWorkflowNumber, page.size());
  }

  @Test
  public void testOnlineWorkflow() {
    List<ProcessDefineResp> page =
        getClient().opsForProcess().page(projectCode, null, null, WORKFLOW_NAME);
    Assert.assertTrue(getClient().opsForProcess().online(projectCode, page.get(0).getCode()));
  }

  @Test
  public void testOfflineWorkflow() {
    List<ProcessDefineResp> page =
        getClient().opsForProcess().page(projectCode, null, null, WORKFLOW_NAME);
    Assert.assertTrue(getClient().opsForProcess().offline(projectCode, page.get(0).getCode()));
  }

  /** the workflow must in offline state */
  @Test
  public void testDeleteWorkflow() {
    List<ProcessDefineResp> page =
        getClient().opsForProcess().page(projectCode, null, null, WORKFLOW_NAME);
    Assert.assertTrue(getClient().opsForProcess().delete(projectCode, page.get(0).getCode()));
  }
}
