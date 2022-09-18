package com.github.weaksloth.dolphins.instance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.weaksloth.dolphins.process.ProcessDefineResp;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/** copied from org.apache.dolphinscheduler.dao.entity.ProcessInstance */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessInstanceQueryResp {

  /** id */
  private int id;

  /** process definition code */
  private Long processDefinitionCode;

  /** process definition version */
  private int processDefinitionVersion;

  /** process state */
  private String state;
  /** recovery flag for failover */
  private String recovery;
  /** start time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date startTime;

  /** end time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date endTime;

  /** run time */
  private int runTimes;

  /** name */
  private String name;

  /** host */
  private String host;

  /** process definition structure */
  private ProcessDefineResp processDefinition;
  /** process command type */
  private String commandType;

  /** command parameters */
  private String commandParam;

  /** node depend type */
  private String taskDependType;

  /** task max try times */
  private int maxTryTimes;

  /** failure strategy when task failed.continue or end */
  private String failureStrategy;

  /** warning type */
  private String warningType;

  /** warning group */
  private Integer warningGroupId;

  /** schedule time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date scheduleTime;

  /** command start time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date commandStartTime;

  /** user define parameters string */
  private String globalParams;

  /** executor id */
  private int executorId;

  /** executor name */
  private String executorName;

  /** tenant code */
  private String tenantCode;

  /** queue */
  private String queue;

  /** process is sub process */
  private String isSubProcess;

  /** task locations for web */
  private String locations;

  /** history command */
  private String historyCmd;

  /** depend processes schedule time */
  private String dependenceScheduleTimes;

  /**
   * process duration
   *
   * @return
   */
  private String duration;

  /** process instance priority */
  private String processInstancePriority;

  /** worker group */
  private String workerGroup;

  /** environment code */
  private Long environmentCode;

  /** process timeout for warning */
  private int timeout;

  /** tenant id */
  private int tenantId;

  /** varPool string */
  private String varPool;

  /** dry run flag */
  private int dryRun;

  /** re-start time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date restartTime;
}
