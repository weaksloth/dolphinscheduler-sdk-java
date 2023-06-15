/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.weaksloth.dolphins.taskinstance;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.weaksloth.dolphins.instance.ProcessInstanceQueryResp;
import com.github.weaksloth.dolphins.process.ProcessDefineResp;
import com.github.weaksloth.dolphins.process.TaskDefinition;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * copied from org.apache.dolphinscheduler.dao.entity.TaskInstance
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInstanceQueryResp implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * task name
     */
    private String name;

    /**
     * task type
     */
    private String taskType;

    private int processInstanceId;

    private String processInstanceName;

    private Long projectCode;

    private long taskCode;

    private int taskDefinitionVersion;

    private String processDefinitionName;

    /**
     * process instance name
     */
    private int taskGroupPriority;

    /**
     * state
     */
    private String state;

    /**
     * task first submit time.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date firstSubmitTime;

    /**
     * task submit time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;

    /**
     * task start time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * task end time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * task host
     */
    private String host;

    /**
     * task shell execute path and the resource down from hdfs
     * default path: $base_run_dir/processInstanceId/taskInstanceId/retryTimes
     */
    private String executePath;

    /**
     * task log path
     * default path: $base_run_dir/processInstanceId/taskInstanceId/retryTimes
     */
    private String logPath;

    /**
     * retry times
     */
    private int retryTimes;

    /**
     * alert flag
     */
    private String alertFlag;

    /**
     * process instance
     */
    private ProcessInstanceQueryResp processInstance;

    /**
     * process definition
     */
    private ProcessDefineResp processDefine;

    /**
     * task definition
     */
    private TaskDefinition taskDefine;

    /**
     * process id
     */
    private int pid;

    /**
     * appLink
     */
    private String appLink;

    /**
     * flag
     */
    private String flag;

    /**
     * task is cache: yes/no
     */
    private String isCache;

    /**
     * cache_key
     */
    private String cacheKey;

    /**
     * dependency
     */
    private String dependency;

    /**
     * switch dependency
     */
    private String switchDependency;

    /**
     * duration
     */
    private String duration;

    /**
     * max retry times
     */
    private int maxRetryTimes;

    /**
     * task retry interval, unit: minute
     */
    private int retryInterval;

    /**
     * task intance priority
     */
    private String taskInstancePriority;

    /**
     * process intance priority
     */
    private String processInstancePriority;

    /**
     * dependent state
     */
    private String dependentResult;

    /**
     * workerGroup
     */
    private String workerGroup;

    /**
     * environment code
     */
    private Long environmentCode;

    /**
     * environment config
     */
    private String environmentConfig;

    /**
     * executor id
     */
    private int executorId;

    /**
     * varPool string
     */
    private String varPool;

    private String executorName;

    private Map<String, String> resources;

    /**
     * delay execution time.
     */
    private int delayTime;

    /**
     * task params
     */
    private String taskParams;

    /**
     * dry run flag
     */
    private int dryRun;
    /**
     * task group id
     */
    private int taskGroupId;

    /**
     * cpu quota
     */
    private Integer cpuQuota;

    /**
     * max memory
     */
    private Integer memoryMax;

    /**
     * task execute type
     */
    private String taskExecuteType;

    /**
     * test flag
     */
    private int testFlag;

}
