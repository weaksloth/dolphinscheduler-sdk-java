package com.github.weaksloth.dolphins.taskinstance;

import com.github.weaksloth.dolphins.BaseTest;
import java.util.List;
import org.junit.Test;

public class TaskInstanceTest extends BaseTest {

  @Test
  public void testPage() {
    Long processInstanceId = 1L;
    List<TaskInstanceQueryResp> taskInstanceQueryResps =
        getClient().opsForTaskInstance().page(projectCode, null, null, processInstanceId);

    taskInstanceQueryResps.forEach(System.out::println);
  }

  @Test
  public void testQueryLog() {
    Long taskInstanceId = 1L;
    String log = getClient().opsForTaskInstance().queryLog(projectCode, null, null, taskInstanceId);

    System.out.println(log);
  }
}
