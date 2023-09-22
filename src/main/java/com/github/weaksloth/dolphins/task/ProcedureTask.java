package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.process.Parameter;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProcedureTask extends AbstractTask {

  /** datasource type */
  private String type;

  /** datasource id */
  private Integer datasource;

  private String method;

  /** resource list */
  private List<TaskResource> resourceList = Collections.emptyList();

  private List<Parameter> localParams = Collections.emptyList();

  @Override
  public String getTaskType() {
    return "PROCEDURE";
  }
}
