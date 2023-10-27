package com.github.weaksloth.dolphins.schedule;

import com.github.weaksloth.dolphins.BaseTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ScheduleTest extends BaseTest {

  public static final Long WORKFLOW_CODE = 11386905160832L;

  /** the workflow must in online state */
  @Test
  public void testCreate() {
    ScheduleDefineParam scheduleDefineParam = new ScheduleDefineParam();
    scheduleDefineParam
        .setProcessDefinitionCode(WORKFLOW_CODE)
        .setSchedule(
            new ScheduleDefineParam.Schedule()
                .setStartTime("2023-10-27 00:00:00")
                .setEndTime("2024-09-20 00:00:00")
                .setCrontab("0 0 * * * ? *"));
    ScheduleInfoResp scheduleInfoResp =
        getClient().opsForSchedule().create(projectCode, scheduleDefineParam);
    System.out.println(scheduleInfoResp);
  }

  @Test
  public void testGetByProject() {
    List<ScheduleInfoResp> resp =
        getClient().opsForSchedule().getByWorkflowCode(projectCode, WORKFLOW_CODE);
    System.out.println(resp);
    Assert.assertEquals(1, resp.size());
  }

  @Test
  public void testOnline() {
    List<ScheduleInfoResp> resp =
        getClient().opsForSchedule().getByWorkflowCode(projectCode, WORKFLOW_CODE);
    long id = resp.get(0).getId();
    Assert.assertTrue(getClient().opsForSchedule().online(projectCode, id));
  }

  @Test
  public void testOffline() {
    List<ScheduleInfoResp> resp =
        getClient().opsForSchedule().getByWorkflowCode(projectCode, WORKFLOW_CODE);
    long id = resp.get(0).getId();
    Assert.assertTrue(getClient().opsForSchedule().offline(projectCode, id));
  }

  @Test
  public void testDelete() {
    List<ScheduleInfoResp> resp =
        getClient().opsForSchedule().getByWorkflowCode(projectCode, WORKFLOW_CODE);
    long id = resp.get(0).getId();
    Assert.assertTrue(getClient().opsForSchedule().delete(projectCode, id));
  }
}
