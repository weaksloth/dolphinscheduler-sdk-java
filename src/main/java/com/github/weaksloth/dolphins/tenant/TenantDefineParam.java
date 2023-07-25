package com.github.weaksloth.dolphins.tenant;

import lombok.Data;
import lombok.experimental.Accessors;

/** create or update tenant param */
@Data
@Accessors(chain = true)
public class TenantDefineParam {

  private String tenantCode;

  /** the default queue id is 1, which means default in dolphin scheduler */
  private Integer queueId = 1;

  private String description;
}
