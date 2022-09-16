package com.github.weaksloth.dolphins.enums;

import static java.util.stream.Collectors.toMap;

import com.google.common.base.Functions;
import java.util.Arrays;
import java.util.Map;

/** copied from org.apache.dolphinscheduler.spi.enums.DbType */
public enum DbTypeEnum {
  MYSQL(0, "mysql"),
  POSTGRESQL(1, "postgresql"),
  HIVE(2, "hive"),
  SPARK(3, "spark"),
  CLICKHOUSE(4, "clickhouse"),
  ORACLE(5, "oracle"),
  SQLSERVER(6, "sqlserver"),
  DB2(7, "db2"),
  PRESTO(8, "presto"),
  H2(9, "h2");

  private final int code;
  private final String descp;

  DbTypeEnum(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  public int getCode() {
    return code;
  }

  public String getDescp() {
    return descp;
  }

  private static final Map<Integer, DbTypeEnum> DB_TYPE_MAP =
      Arrays.stream(DbTypeEnum.values()).collect(toMap(DbTypeEnum::getCode, Functions.identity()));

  public static DbTypeEnum of(int type) {
    if (DB_TYPE_MAP.containsKey(type)) {
      return DB_TYPE_MAP.get(type);
    }
    return null;
  }
}
