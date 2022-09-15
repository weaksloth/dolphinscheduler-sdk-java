package com.github.weaksloth.dolphins.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/** 重新运行/恢复失败 工作流实例参数 */
@Data
@Accessors(chain = true)
public class ProcessInstanceRunParam {

  private String processInstanceId;

  private String executeType;
}
