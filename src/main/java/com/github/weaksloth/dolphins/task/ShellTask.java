package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.process.Parameter;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShellTask extends AbstractTask {

  /** resource list */
  private List<TaskResource> resourceList;

  private List<Parameter> localParams;

  /** shell script */
  private String rawScript;

  @Override
  public String getTaskType() {
    return "SHELL";
  }
}
