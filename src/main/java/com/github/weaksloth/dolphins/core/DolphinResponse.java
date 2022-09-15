package com.github.weaksloth.dolphins.core;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class DolphinResponse {

  private Integer code;

  private String msg;

  private JsonNode data;

  private Boolean success;

  private Boolean failed;
}
