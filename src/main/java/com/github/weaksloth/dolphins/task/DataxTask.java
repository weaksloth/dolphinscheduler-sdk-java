package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.enums.DbTypeEnum;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** reference: org.apache.dolphinscheduler.plugin.task.datax.DataxParameters */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DataxTask extends AbstractTask {

  /**
   * do not custom config
   *
   * <p>0: write sql, dolphin scheduler will construct datax config
   *
   * <p>1: give datax config by yourself
   */
  private Integer customConfig = 0;

  /** if customConfig eq 1 ,then json is usable */
  private String json;

  /** datasource type,{@link DbTypeEnum#name()} */
  private String dsType;

  /** datasource id */
  private Long dataSource;

  /** target datasource type */
  private String dtType;

  /** target datasource id */
  private Long dataTarget;

  /** the sql will be executed in "dataSource" and write to "dataTarget" */
  private String sql;

  /** target table */
  private String targetTable;

  private Integer jobSpeedByte = 0;
  private Integer jobSpeedRecord = 1000;
  private List<String> preStatements = Collections.emptyList();
  private List<String> postStatements = Collections.emptyList();
  private Integer xms = 1;
  private Integer xmx = 1;

  @Override
  public String getTaskType() {
    return "DATAX";
  }
}
