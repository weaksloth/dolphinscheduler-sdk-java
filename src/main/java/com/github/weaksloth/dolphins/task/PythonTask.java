package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.process.Parameter;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** reference: org.apache.dolphinscheduler.plugin.task.python.PythonParameters */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PythonTask extends AbstractTask {

  /** resource list */
  private List<TaskResource> resourceList = Collections.emptyList();

  private List<Parameter> localParams = Collections.emptyList();

  /** python script */
  private String rawScript;

  @Override
  public String getTaskType() {
    return "PYTHON";
  }
}
