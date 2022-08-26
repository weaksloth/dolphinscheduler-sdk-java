package com.github.weaksloth.dolphinscheduler.sdk.process;


import lombok.Data;

/** dolphin创建工作流参数 */
@Data
public class ProcessDefinition {

    /** 工作流名称 */
    private String name;

    /** location */
    private String locations;

    /** 任务节点json，内容为json数组 */
    private String taskDefinitionJson;

    /** 任务节点关系json，内容为json数组 */
    private String taskRelationJson;

    /** 租户名称 */
    private String tenantCode;

    /** 工作流描述信息 */
    private String description;

    /** 全局参数 */
    private String globalParams;

    private String timeout;

}
