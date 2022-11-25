package com.github.weaksloth.dolphins.process;

import com.github.weaksloth.dolphins.remote.RequestHttpEntity;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * task parameter or global parameter <br>
 *
 * <p><a
 * href="https://dolphinscheduler.apache.org/zh-cn/docs/latest/user_doc/guide/parameter/global.html">doc
 * in official website</a>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {

  private String prop;

  private String value;

  /** in or out */
  private String direct;

  /** VARCHAR,INTEGER,LONG.... */
  private String type;

  /**
   * get default global parameter instance
   *
   * @return global parameter instance
   */
  public static Parameter getGlobal() {
    Parameter parameter = new Parameter();
    parameter.setDirect("IN");
    parameter.setProp("VARCHAR");
    return parameter;
  }

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
