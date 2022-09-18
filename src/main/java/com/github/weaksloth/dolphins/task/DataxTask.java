package com.github.weaksloth.dolphins.task;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataxTask extends AbstractTask {

  private Integer customConfig = 0;
  private String dsType; // 源类型
  private Long dataSource; // dolphin datasource id
  private String dtType; // 目标类型
  private Long dataTarget; // 目标数据源 id
  private String sql; // 查询语句
  private String targetTable; // 目标表
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
