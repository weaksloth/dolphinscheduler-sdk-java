package com.github.weaksloth.dolphins.process;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProcessReleaseParam {

  public static final String ONLINE_STATE = "ONLINE";
  public static final String OFFLINE_STATE = "OFFLINE";

  /** workflow name, this field is not necessary, the dolphin scheduler rest api is shit!!! */
  private String name;

  /** workflow state： ONLINE or OFFLINE */
  private String releaseState;

  /**
   * create instance with online state
   *
   * @return {@link ProcessReleaseParam} with online state
   */
  public static ProcessReleaseParam newOnlineInstance() {
    return new ProcessReleaseParam().setReleaseState(ONLINE_STATE);
  }

  /**
   * create instance with offline state
   *
   * @return {@link ProcessReleaseParam} with offline state
   */
  public static ProcessReleaseParam newOfflineInstance() {
    return new ProcessReleaseParam().setReleaseState(OFFLINE_STATE);
  }
}
