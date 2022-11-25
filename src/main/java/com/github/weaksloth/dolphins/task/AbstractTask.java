package com.github.weaksloth.dolphins.task;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class AbstractTask {

  protected ObjectNode dependence;
  protected ObjectNode conditionResult;
  protected ObjectNode waitStartTimeout;
  protected ObjectNode switchResult;

  public abstract String getTaskType();
}
