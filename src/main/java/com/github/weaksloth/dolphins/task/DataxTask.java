package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.enums.DbTypeEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

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
  private List<String> preStatements;
  private List<String> postStatements = new ArrayList<>();
  private Integer xms = 1;
  private Integer xmx = 1;

  @Override
  public String getTaskType() {
    return "DATAX";
  }
}
