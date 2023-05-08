package com.github.weaksloth.dolphins.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;

/** define process response,copied from org.apache.dolphinscheduler.dao.entity.ProcessDefinition */
@Data
public class ProcessDefineResp {

  /** id */
  private int id;

  /** code */
  private long code;

  /** name */
  private String name;

  /** version */
  private int version;

  /** release state : online/offline */
  private String releaseState;

  /** project code */
  private long projectCode;

  /** description */
  private String description;

  /** user defined parameters */
  private String globalParams;

  /** user defined parameter list */
  private List<Parameter> globalParamList;

  /** user define parameter map */
  private Map<String, String> globalParamMap;

  /** create time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /** update time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  /** process is valid: yes/no */
  private String flag;

  /** process user id */
  private int userId;

  /** user name */
  private String userName;

  /** project name */
  private String projectName;

  /** locations array for web */
  private String locations;

  /** schedule release state : online/offline */
  private String scheduleReleaseState;

  /** process warning time out. unit: minute */
  private int timeout;

  /** tenant id */
  private int tenantId;

  /** tenant code */
  private String tenantCode;

  /** modify user name */
  private String modifyBy;

  /** warningGroupId */
  private int warningGroupId;

  private String executionType;
}
