package com.github.weaksloth.dolphins.task;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public abstract class AbstractTask {

  protected ObjectNode dependence;
  protected ObjectNode conditionResult;
  protected ObjectNode waitStartTimeout;
  protected ObjectNode switchResult;
}
