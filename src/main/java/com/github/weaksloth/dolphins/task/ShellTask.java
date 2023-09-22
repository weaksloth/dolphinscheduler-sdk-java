package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.process.Parameter;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ShellTask extends AbstractTask {

  /** resource list */
  private List<TaskResource> resourceList = Collections.emptyList();

  private List<Parameter> localParams = Collections.emptyList();

  /** shell script */
  private String rawScript;

  @Override
  public String getTaskType() {
    return "SHELL";
  }
}
