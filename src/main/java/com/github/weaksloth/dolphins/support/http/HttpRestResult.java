package com.github.weaksloth.dolphins.support.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dolphin scheduler rest api response result
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRestResult<T> {

  private Integer code;

  private String msg;

  private T data;

  private Boolean success;

  private Boolean failed;
}
