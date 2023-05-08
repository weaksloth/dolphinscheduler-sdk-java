package com.github.weaksloth.dolphins.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/** copied from org.apache.dolphinscheduler.dao.entity.Schedule */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleInfoResp {

  private int id;

  /** process definition code */
  private long processDefinitionCode;

  /** process definition name */
  private String processDefinitionName;

  /** project name */
  private String projectName;

  /** schedule description */
  private String definitionDescription;

  /** schedule start time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date startTime;

  /** schedule end time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date endTime;

  /**
   * timezoneId
   *
   * <p>see {@link java.util.TimeZone#getTimeZone(String)}
   */
  private String timezoneId;

  /** crontab expression */
  private String crontab;

  /** failure strategy */
  private String failureStrategy;

  /** warning type */
  private String warningType;

  /** create time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /** update time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  /** created user id */
  private int userId;

  /** created user name */
  private String userName;

  /** release state */
  private String releaseState;

  /** warning group id */
  private int warningGroupId;

  /** process instance priority */
  private String processInstancePriority;

  /** worker group */
  private String workerGroup;

  /** environment code */
  private Long environmentCode;
}
