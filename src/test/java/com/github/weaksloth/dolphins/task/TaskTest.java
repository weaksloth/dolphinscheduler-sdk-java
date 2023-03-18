package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.process.ProcessDefineParam;
import com.github.weaksloth.dolphins.process.TaskDefinition;
import com.github.weaksloth.dolphins.process.TaskRelation;
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

    // only one task,so only need to set post task code
    TaskRelation taskRelation = new TaskRelation().setPostTaskCode(taskCode);

    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName("test-shell-task-dag")
        .setLocations(TaskLocationUtils.verticalLocation(taskCode))
        .setDescription("test-shell-task")
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setTaskDefinitionJson(Collections.singletonList(taskDefinition))
        .setTaskRelationJson(Collections.singletonList(taskRelation))
        .setGlobalParams(null);

    System.out.println(getClient().opsForProcess().create(projectCode, pcr));
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
        .setConnectTimeout(60000)
        .setSocketTimeout(60000)
        .setConditionResult(TaskUtils.createEmptyConditionResult());

    // use utils to create task definition with default config
    TaskDefinition taskDefinition =
        TaskDefinitionUtils.createDefaultTaskDefinition(taskCode, httpTask);

    ProcessDefineParam pcr = new ProcessDefineParam();
    pcr.setName("test-http-task-dag")
        .setLocations(TaskLocationUtils.verticalLocation(taskCode))
        .setDescription("test-shell-task")
        .setTenantCode(tenantCode)
        .setTimeout("0")
        .setTaskDefinitionJson(Collections.singletonList(taskDefinition))
        .setTaskRelationJson(TaskRelationUtils.oneLineRelation(taskCode))
        .setGlobalParams(null);

    System.out.println(getClient().opsForProcess().create(projectCode, pcr));
  }

  @Test
  public void testGenerateTaskCode() {
    int expectedCodeNumber = 10;
    List<Long> taskCodes =
        super.getClient().opsForProcess().generateTaskCode(projectCode, expectedCodeNumber);
    Assert.assertEquals(expectedCodeNumber, taskCodes.size());
  }
}
