package com.github.weaksloth.dolphins.enums;

public enum CacheType {
  TENANT("tenant"),
  USER("user"),
  QUEUE("queue"),
  PROCESS_DEFINITION("processDefinition"),
  PROCESS_TASK_RELATION("processTaskRelation"),
  TASK_DEFINITION("taskDefinition"),
  WORKER_GROUP("workerGroup"),
  SCHEDULE("schedule");

  CacheType(String cacheName) {
    this.cacheName = cacheName;
  }

  private final String cacheName;

  public String getCacheName() {
    return cacheName;
  }
}
