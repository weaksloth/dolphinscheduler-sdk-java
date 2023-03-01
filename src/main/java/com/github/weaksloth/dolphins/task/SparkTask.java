package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.process.Parameter;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SparkTask extends AbstractTask {

  private String appName;
  private TaskResource mainJar;
  private String mainClass;
  private String mainArgs;

  /** optional value:cluster,client,local */
  private String deployMode;

  // spark task resource
  private Integer driverCores;
  private String driverMemory;
  private Integer numExecutors;
  private Integer executorCores;
  private String executorMemory;

  private String others;

  /** optional value:JAVA,SCALA,PYTHON */
  private String programType;

  /** optional value:SPARK2,SPARK1 */
  private String sparkVersion;

  private List<Parameter> localParams = Collections.emptyList();

  private List<TaskResource> resourceList = Collections.emptyList();

  public static SparkTask newV2Instance() {
    return new SparkTask().setSparkVersion("SPARK2");
  }

  public SparkTask inClientMode() {
    this.deployMode = "client";
    return this;
  }

  public SparkTask inClusterMode() {
    this.deployMode = "cluster";
    return this;
  }

  @Override
  public String getTaskType() {
    return "SPARK";
  }
}
