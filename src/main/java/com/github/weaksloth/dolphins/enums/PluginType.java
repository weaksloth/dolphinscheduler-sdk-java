package com.github.weaksloth.dolphins.enums;

import java.util.HashMap;

/** PluginType */
public enum PluginType {
  ALERT(1, "alert", true),
  REGISTER(2, "register", false),
  TASK(3, "task", true);

  PluginType(int code, String desc, boolean hasUi) {
    this.code = code;
    this.desc = desc;
    this.hasUi = hasUi;
  }

  private final int code;
  private final String desc;
  private final boolean hasUi;

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public boolean getHasUi() {
    return hasUi;
  }

  private static HashMap<Integer, PluginType> PLUGIN_TYPE_MAP = new HashMap<>();

  static {
    for (PluginType pluginType : PluginType.values()) {
      PLUGIN_TYPE_MAP.put(pluginType.getCode(), pluginType);
    }
  }

  public static PluginType of(int type) {
    if (PLUGIN_TYPE_MAP.containsKey(type)) {
      return PLUGIN_TYPE_MAP.get(type);
    }
    throw new IllegalArgumentException("invalid type : " + type);
  }
}
