package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.process.*;
import com.github.weaksloth.dolphins.util.TaskDefinitionUtils;
import com.github.weaksloth.dolphins.util.TaskLocationUtils;
import com.github.weaksloth.dolphins.util.TaskRelationUtils;
import com.github.weaksloth.dolphins.util.TaskUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest extends BaseTest {

  @Test
  public void testShellTask() {
    Long taskCode = getClient().opsForProcess().generateTaskCode(projectCode, 1).get(0);
    ShellTask shellTask = new ShellTask();
    shellTask.setRawScript("echo 'hello dolphin scheduler java sdk'");

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, shellTask);

    submit(taskCode, taskDefinition, "test-shell-task-dag", "test-shell-task");
  }

  @Test
  public void testHttpTask() {
    Long taskCode = getClient().opsForProcess().generateTaskCode(projectCode, 1).get(0);

    HttpTask httpTask = new HttpTask();
    httpTask
        .setUrl("http://www.baidu.com")
        .setHttpMethod("GET")
        .setHttpCheckCondition("STATUS_CODE_DEFAULT")
        .setCondition("")
        .setConditionResult(TaskUtils.createEmptyConditionResult());

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, httpTask);

    submit(taskCode, taskDefinition, "test-http-task-dag", "test-shell-task");
  }

  /** run this test before creating datasource and then set its id to SqlTask */
  @Test
  public void testSqlTask() {
    Long taskCode = getClient().opsForProcess().generateTaskCode(projectCode, 1).get(0);

    SqlTask sqlTask = new SqlTask();
    sqlTask
        .setType("MYSQL")
        .setDatasource(1)
        .setSql("select 1")
        .setSqlType("0")
        .setSendEmail(false)
        .setDisplayRows(10)
        .setTitle("")
        .setGroupId(null)
        .setConnParams("")
        .setConditionResult(TaskUtils.createEmptyConditionResult());

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, sqlTask);

    submit(taskCode, taskDefinition, "test-sql-task-dag", "test-sql-task");
  }

  @Test
  public void testPythonTask() {
    Long taskCode = getClient().opsForProcess().generateTaskCode(projectCode, 1).get(0);
    PythonTask pythonTask = new PythonTask();
    pythonTask.setRawScript("print('hello dolphin scheduler sdk.')");

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, pythonTask);

    submit(taskCode, taskDefinition, "test-python-task-dag", "test-python-task");
  }

  @Test
  public void testConditionTask() {
    List<Long> taskCodes = getClient().opsForProcess().generateTaskCode(projectCode, 4);

    // -------------building task------------------
    // shell task
    Long shellTaskCode = taskCodes.get(0);
    ShellTask shellTask = new ShellTask();
    shellTask.setRawScript("echo 'hello dolphin scheduler java sdk'");
    TaskDefinition shellTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition("shell-1", shellTaskCode, shellTask);

    // success task
    Long successTaskCode = taskCodes.get(1);
    ShellTask successTask = new ShellTask();
    successTask.setRawScript("echo 'success'");
    TaskDefinition successTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(
            "shell-success", successTaskCode, successTask);

    // fail task
    Long failTaskCode = taskCodes.get(2);
    ShellTask failTask = new ShellTask();
    failTask.setRawScript("echo 'fail'");
    TaskDefinition failTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition("shell-fail", failTaskCode, failTask);

    // condition task
    Long conditionTaskCode = taskCodes.get(3);
    ConditionTask conditionTask =
        TaskUtils.buildConditionTask(
            successTaskDefinition.getCode(),
            failTaskDefinition.getCode(),
            Collections.singletonList(shellTaskDefinition.getCode()));
    TaskDefinition conditionTaskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(
            "condition", conditionTaskCode, conditionTask);
    // ----------------end of building task--------------------

    // -----------building relation---------------
    TaskRelation r1 = new TaskRelation().setPostTaskCode(shellTaskCode);
    TaskRelation r2 =
        new TaskRelation().setPreTaskCode(shellTaskCode).setPostTaskCode(conditionTaskCode);
    TaskRelation r3 =
        new TaskRelation().setPreTaskCode(conditionTaskCode).setPostTaskCode(successTaskCode);
    TaskRelation r4 =
        new TaskRelation().setPreTaskCode(conditionTaskCode).setPostTaskCode(failTaskCode);
    // ------------end of building relation----------

    // set locations
    TaskLocation tl1 = new TaskLocation(shellTaskCode, 200, 340);
    TaskLocation tl2 = new TaskLocation(conditionTaskCode, 500, 340);
    TaskLocation tl3 = new TaskLocation(successTaskCode, 800, 240);
    TaskLocation tl4 = new TaskLocation(failTaskCode, 800, 440);

    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName("condition-dag")
        .setLocations(Arrays.asList(tl1, tl2, tl3, tl4))
        .setDescription("test for use condition dag")
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setExecutionType(ProcessDefineParam.EXECUTION_TYPE_PARALLEL)
        .setTaskDefinitionJson(
            Arrays.asList(
                shellTaskDefinition,
                successTaskDefinition,
                failTaskDefinition,
                conditionTaskDefinition))
        .setTaskRelationJson(Arrays.asList(r1, r2, r3, r4))
        .setGlobalParams(null);

    ProcessDefineResp resp = getClient().opsForProcess().create(projectCode, pcr);
    System.out.println(resp);
    Assert.assertEquals("condition-dag", resp.getName());
  }

  private void submit(
      Long taskCode, TaskDefinition taskDefinition, String processName, String description) {
    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName(processName)
        .setLocations(TaskLocationUtils.verticalLocation(taskCode))
        .setDescription(description)
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setExecutionType(ProcessDefineParam.EXECUTION_TYPE_PARALLEL)
        .setTaskDefinitionJson(Collections.singletonList(taskDefinition))
        .setTaskRelationJson(TaskRelationUtils.oneLineRelation(taskCode))
        .setGlobalParams(null);

    ProcessDefineResp resp = getClient().opsForProcess().create(projectCode, pcr);
    System.out.println(resp);
    Assert.assertEquals(processName, resp.getName());
  }

  @Test
  public void testGenerateTaskCode() {
    int expectedCodeNumber = 10;
    List<Long> taskCodes =
        super.getClient().opsForProcess().generateTaskCode(projectCode, expectedCodeNumber);
    Assert.assertEquals(expectedCodeNumber, taskCodes.size());
  }
}
