package com.github.weaksloth.dolphins.util;

import com.github.weaksloth.dolphins.process.TaskRelation;
import java.util.ArrayList;
import java.util.List;

/** utils for build task relation */
public class TaskRelationUtils {

  /**
   * this method is used for graph edge set task one by one relation,such as t1->t2->t3->t4.....
   *
   * @param taskCodes task codes
   * @return {@link List<TaskRelation>}
   */
  public static List<TaskRelation> oneLineRelation(Long... taskCodes) {
    List<TaskRelation> list = new ArrayList<>();
    for (int i = 0; i < taskCodes.length; i++) {
      if (i == 0) {
        list.add(new TaskRelation().setPostTaskCode(taskCodes[i]));
      } else {
        list.add(new TaskRelation().setPreTaskCode(taskCodes[i - 1]).setPostTaskCode(taskCodes[i]));
      }
    }
    return list;
  }

  /**
   * this method is used for graph edge all task is alone,no relation,such as t1 t2 t3
   *
   * @param taskCodes task codes
   * @return {@link List<TaskRelation>}
   */
  public static List<TaskRelation> noRelation(Long... taskCodes) {
    List<TaskRelation> list = new ArrayList<>();
    for (Long taskCode : taskCodes) {
      list.add(new TaskRelation().setPostTaskCode(taskCode));
    }
    return list;
  }
}
