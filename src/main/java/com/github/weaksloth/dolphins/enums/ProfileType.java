package com.github.weaksloth.dolphins.enums;

import com.google.common.collect.Lists;
import java.util.List;

public enum ProfileType {
  ;

  public static final String H2 = "h2";

  public static final String MYSQL = "mysql";

  public static final String POSTGRESQL = "postgresql";

  public static final List<String> DATASOURCE_PROFILE = Lists.newArrayList(H2, MYSQL, POSTGRESQL);
}
