package com.github.weaksloth.dolphins.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/** create project response,copied from org.apache.dolphinscheduler.dao.entity.Project */
@Data
public class ProjectInfoResp {

  /** id */
  private int id;

  /** user id */
  private int userId;

  /** user name */
  private String userName;

  /** project code */
  private long code;

  /** project name */
  private String name;

  /** project description */
  private String description;

  /** create time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /** update time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  /** permission */
  private int perm;

  /** process define count */
  private int defCount;

  /** process instance running count */
  private int instRunningCount;
}
