package com.github.weaksloth.dolphins.resource;

import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/** create resource param */
@Data
@Accessors(chain = true)
public class ResourceCreateParam {

  private String type = DolphinClientConstant.Resource.TYPE_FILE;

  private String pid = DolphinClientConstant.Resource.DEFAULT_PID_FILE;

  private String currentDir = DolphinClientConstant.Resource.DEFAULT_CURRENT_DIR;

  private String fileName;

  private String suffix;

  private String description;

  private String content;
}
