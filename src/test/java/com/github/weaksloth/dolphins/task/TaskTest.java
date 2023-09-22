package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.process.ProcessDefineParam;
import com.github.weaksloth.dolphins.process.ProcessDefineResp;
import com.github.weaksloth.dolphins.process.TaskDefinition;
import com.github.weaksloth.dolphins.util.TaskDefinitionUtils;
import com.github.weaksloth.dolphins.util.TaskLocationUtils;
import com.github.weaksloth.dolphins.util.TaskRelationUtils;
import com.github.weaksloth.dolphins.util.TaskUtils;
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
