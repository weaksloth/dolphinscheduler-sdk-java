package com.github.weaksloth.dolphins.resource;

import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import java.io.File;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResourceUploadParam {

  private String type = DolphinClientConstant.Resource.TYPE_FILE;

  private String pid = DolphinClientConstant.Resource.DEFAULT_PID_FILE;

  private String currentDir = DolphinClientConstant.Resource.DEFAULT_CURRENT_DIR;

  private String description;

  private File file;

  private String name;
}
