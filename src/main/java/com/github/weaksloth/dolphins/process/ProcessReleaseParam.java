package com.github.weaksloth.dolphins.process;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProcessReleaseParam {

  /** workflow name */
  private String name;

  /** workflow state： ONLINE or OFFLINE */
  private String releaseState;

  public String getOnlineState() {
    return "ONLINE";
  }

  public String getOfflineState() {
    return "OFFLINE";
  }
}
