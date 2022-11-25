package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import lombok.Data;

/**
 * used by shell,python,spark,flink.... task
 *
 * <p>used when define task
 */
@Data
public class TaskResource {

  private Long id;

  /**
   * must rewrite,then {@link RequestHttpEntity#bodyToMap()} can transfer object to json string
   *
   * @return object json string
   */
  @Override
  public String toString() {
    return JacksonUtils.toJSONString(this);
  }
}
