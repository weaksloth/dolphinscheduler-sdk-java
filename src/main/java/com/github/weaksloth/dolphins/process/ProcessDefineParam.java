package com.github.weaksloth.dolphins.process;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * dolphin scheduler define process param
 *
 * <p>dolphin scheduler use post form type to create process,so in fact every attribute is string
 * type
 *
 * <p>but in order to develop easier,we use TaskLocation,TaskDefinition,TaskRelation object,and
 * rewrite toString method
 *
 * <p>so that,when begin to send request,we will transfer this object to json string
 */
@Data
@Accessors(chain = true)
public class ProcessDefineParam {

  /** workflow name */
  private String name;

  /** location */
  private List<TaskLocation> locations;

  private List<TaskDefinition> taskDefinitionJson;

  private List<TaskRelation> taskRelationJson;

  /** tenant code */
  private String tenantCode;

  /** desc for workflow */
  private String description;

  /** global params */
  private List<Parameter> globalParams;

  private String timeout;
}
