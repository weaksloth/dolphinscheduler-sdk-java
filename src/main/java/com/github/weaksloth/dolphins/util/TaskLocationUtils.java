package com.github.weaksloth.dolphins.util;

import com.github.weaksloth.dolphins.process.TaskLocation;
import java.util.ArrayList;
import java.util.List;

/** utils for node in dag graph's location */
public class TaskLocationUtils {

  /**
   * this method is used for graph node get task location one by one,such as
   * (100,200),(300,200),(500,200)
   *
   * @param taskCodes task codes
   * @return {@link List<TaskLocation>}
   */
  public static List<TaskLocation> horizontalLocation(Long... taskCodes) {
    int beginX = 100;
    int y = 100;
    int xStep = 200;
    return horizontalLocation(beginX, y, xStep, taskCodes);
  }

  /**
   * this method is used for graph node get task location with custom x,y,step one by one,such as
   * (beginX,y),(beginX+step,y)....
   *
   * @param beginX location of begin x
   * @param xStep step of x
   * @param y location of y
   * @param taskCodes task codes
   * @return {@link List<TaskLocation>}
   */
  public static List<TaskLocation> horizontalLocation(
      int beginX, int y, int xStep, Long... taskCodes) {
    List<TaskLocation> list = new ArrayList<>();
    for (Long taskCode : taskCodes) {
      list.add(new TaskLocation(taskCode, beginX, y));
      beginX += xStep;
    }
    return list;
  }

  /**
   * this method is used for graph node get task location one by one,such as
   * (100,100),(100,300),(100,500)
   *
   * @param taskCodes task codes
   * @return {@link List<TaskLocation>}
   */
  public static List<TaskLocation> verticalLocation(Long... taskCodes) {
    int x = 100;
    int beginY = 100;
    int yStep = 200;
    return verticalLocation(x, beginY, yStep, taskCodes);
  }

  /**
   * @param x location of x
   * @param beginY location of begin y
   * @param yStep step of y
   * @param taskCodes task codes
   * @return {@link List<TaskLocation>}
   */
  public static List<TaskLocation> verticalLocation(
      int x, int beginY, int yStep, Long... taskCodes) {
    List<TaskLocation> list = new ArrayList<>();
    for (Long taskCode : taskCodes) {
      list.add(new TaskLocation(taskCode, x, beginY));
      beginY += yStep;
    }
    return list;
  }
}
