package com.github.weaksloth.dolphins.task;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Generic task for handling unknown or custom task types. Captures the raw task parameters as a
 * JsonNode.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GenericTask extends AbstractTask {

  private String taskType;
  private JsonNode taskParams;

  @Override
  public String getTaskType() {
    return this.taskType;
  }
}
