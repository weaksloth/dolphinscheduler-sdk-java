package com.github.weaksloth.dolphins.schedule;

import com.github.weaksloth.dolphins.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ScheduleTest extends BaseTest {

  /** the workflow must in offline state */
  @Test
  public void testCreate() {
    Long workflowCode = 6920291870528L;
    ScheduleDefineParam scheduleDefineParam = new ScheduleDefineParam();
    scheduleDefineParam
        .setProcessDefinitionCode(workflowCode)
        .setSchedule(
            new ScheduleDefineParam.Schedule()
                .setStartTime("2022-09-18 00:00:00")
                .setEndTime("2022-09-20 00:00:00")
                .setCrontab("0 0 * * * ? *"));
    ScheduleDefineResp scheduleDefineResp =
        getClient().opsForSchedule().create(projectCode, scheduleDefineParam);
    System.out.println(scheduleDefineResp);
  }

  @Test
  public void testOnline() {
    Long id = 2L;
    Assert.assertTrue(getClient().opsForSchedule().online(projectCode, id));
  }

  @Test
  public void testOffline() {
    Long id = 2L;
    Assert.assertTrue(getClient().opsForSchedule().offline(projectCode, id));
  }

  @Test
  public void testDelete() {
    Long id = 2L;
    Assert.assertTrue(getClient().opsForSchedule().delete(projectCode, id));
  }
}
