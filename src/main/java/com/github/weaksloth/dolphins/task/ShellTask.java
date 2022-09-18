package com.github.weaksloth.dolphins.task;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShellTask extends AbstractTask {

  private ArrayNode resourceList; // 资源列表
  private ArrayNode localParams;
  private String rawScript; // shell脚本

  @Override
  public String getTaskType() {
    return "SHELL";
  }
}
