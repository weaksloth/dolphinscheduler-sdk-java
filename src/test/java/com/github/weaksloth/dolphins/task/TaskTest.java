package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.BaseTest;
import org.junit.Test;

public class TaskTest extends BaseTest {

  @Test
  public void testGenerateTaskCode() {
    System.out.println(super.getClient().generateTaskCode(projectCode, 10));
  }
}
