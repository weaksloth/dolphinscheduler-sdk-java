package com.github.weaksloth.dolphins.task;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HttpTask extends AbstractTask {

  private ArrayNode localParams;
  private ArrayNode httpParams; // http 参数
  private String url;
  private String httpMethod; // POST GET
  private String httpCheckCondition; // STATUS_CODE_DEFAULT
  private String condition;
  private Integer connectTimeout;
  private Integer socketTimeout;

  @Override
  public String getTaskType() {
    return "HTTP";
  }

  @Data
  @Accessors(chain = true)
  @AllArgsConstructor
  @NoArgsConstructor
  public static class HttpParams {
    private String prop;
    private String value;
    private String parameterType;
  }
}
