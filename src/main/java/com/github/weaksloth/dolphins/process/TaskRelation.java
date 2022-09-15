package com.github.weaksloth.dolphins.process;

import lombok.Data;

@Data
public class TaskRelation {

  private String name = "";

  private Long preTaskCode = 0L;

  private Integer preTaskVersion = 0;

  private Long postTaskCode;

  private Integer postTaskVersion = 0;

  private Integer conditionType = 0;

  private Integer conditionParams;
}
