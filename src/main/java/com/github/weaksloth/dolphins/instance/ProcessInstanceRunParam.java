package com.github.weaksloth.dolphins.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/** re run/recover process instance */
@Data
@Accessors(chain = true)
public class ProcessInstanceRunParam {

  private Long processInstanceId;

  private String executeType;
}
