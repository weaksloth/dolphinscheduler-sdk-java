package com.github.weaksloth.dolphins.utils;

import com.github.weaksloth.dolphins.process.TaskLocation;
import com.github.weaksloth.dolphins.util.TaskLocationUtils;
import java.util.List;
import org.junit.Test;

public class TaskLocationUtilsTest {

  @Test
  public void testHorizontalLocation() {
    List<TaskLocation> taskLocations = TaskLocationUtils.horizontalLocation(1L, 2L, 3L, 4L);
    System.out.println(taskLocations);
  }

  @Test
  public void testVerticalLocation() {
    List<TaskLocation> taskLocations = TaskLocationUtils.verticalLocation(1L, 2L, 3L, 4L);
    System.out.println(taskLocations);
  }
}
