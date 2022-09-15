package com.github.weaksloth.dolphins.file;

import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/** dolphin scheduler创建文件参数 */
@Data
@Accessors(chain = true)
public class FileCreateParam {

  private String type = DolphinClientConstant.File.TYPE_FILE;

  private String pid = DolphinClientConstant.File.DEFAULT_PID_FILE;

  private String currentDir = DolphinClientConstant.File.DEFAULT_CURRENT_DIR;

  private String fileName;

  private String suffix;

  private String description;

  private String content;
}
