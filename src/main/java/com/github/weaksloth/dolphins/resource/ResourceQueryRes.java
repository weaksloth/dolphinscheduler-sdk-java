package com.github.weaksloth.dolphins.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Data;

/** copied from org.apache.dolphinscheduler.dao.entity.Resource */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceQueryRes {

  /** id */
  private int id;

  /** parent id */
  private int pid;

  /** resource alias */
  private String alias;

  /** full name */
  private String fullName;

  /** is directory */
  private boolean isDirectory = false;

  /** description */
  private String description;

  /** file alias */
  private String fileName;

  /** user id */
  private int userId;

  /** resource type */
  private String type;

  /** resource size */
  private long size;

  /** create time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /** update time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;
}
