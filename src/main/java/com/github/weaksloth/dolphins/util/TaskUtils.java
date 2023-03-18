package com.github.weaksloth.dolphins.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.weaksloth.dolphins.task.ConditionTask;
import java.util.Collections;
import java.util.List;

/** utils for task node */
public class TaskUtils {

  /**
   * build condition task
   *
   * @param successNodeCode the task to run when condition is success
   * @param failNodeCode the task to run when condition is fail
   * @param dependNodeCodeList dependency task, support multi dependency
   */
  public static ConditionTask buildConditionTask(
      Long successNodeCode, Long failNodeCode, List<Long> dependNodeCodeList) {
    ConditionTask conditionTask = new ConditionTask();
    ObjectNode dependence = JacksonUtils.createObjectNode();
    dependence.put("relation", "AND");

    ArrayNode dependTaskList = JacksonUtils.createArrayNode();
    ObjectNode dependTask = JacksonUtils.createObjectNode();

    ArrayNode dependItemList = JacksonUtils.createArrayNode();

    for (Long dependNodeCode : dependNodeCodeList) {
      ObjectNode dependItem = JacksonUtils.createObjectNode();
      dependItem.put("depTaskCode", dependNodeCode);
      dependItem.put("status", "SUCCESS");
      dependItemList.add(dependItem);
    }

    dependTask.put("relation", "AND");
    dependTask.set("dependItemList", dependItemList);

    dependTaskList.add(dependTask);

    dependence.set("dependTaskList", dependTaskList);
    conditionTask
        .setConditionResult(
            createConditionResult(
                Collections.singletonList(successNodeCode),
                Collections.singletonList(failNodeCode)))
        .setDependence(dependence)
        .setWaitStartTimeout(JacksonUtils.createObjectNode())
        .setSwitchResult(JacksonUtils.createObjectNode());
    return conditionTask;
  }

  /** create empty condition result for most task */
  public static ObjectNode createEmptyConditionResult() {
    ObjectNode conditionResult = JacksonUtils.createObjectNode();
    conditionResult.put("successNode", JacksonUtils.createArrayNode());
    conditionResult.put("failedNode", JacksonUtils.createArrayNode());
    return conditionResult;
  }

  /**
   * create condition result with success node and fail node
   *
   * @param successNodeList the task when condition is success
   * @param failNodeList the task when condition is fail
   */
  public static ObjectNode createConditionResult(
      List<Long> successNodeList, List<Long> failNodeList) {

    ObjectNode conditionResult = JacksonUtils.createObjectNode();
    ArrayNode successNode = JacksonUtils.createArrayNode();
    ArrayNode failNode = JacksonUtils.createArrayNode();

    successNodeList.forEach(successNode::add);
    failNodeList.forEach(failNode::add);

    conditionResult.put("successNode", successNode);
    conditionResult.put("failedNode", failNode);
    return conditionResult;
  }
}
