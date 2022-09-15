package com.github.weaksloth.dolphins.process;

import lombok.Data;
import lombok.experimental.Accessors;

/** dolphin上下线工作流表单参数 */
@Data
@Accessors(chain = true)
public class ProcessReleasefParam {

  /** 工作流名称 */
  private String name;

  /** 工作流状态： ONLINE and OFFLINE */
  private String releaseState;
}
