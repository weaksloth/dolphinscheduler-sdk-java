package com.github.weaksloth.dolphins.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.Query;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/** operator for operate project */
@Slf4j
public class ProjectOperator extends AbstractOperator {

  public ProjectOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * create project, api:/dolphinscheduler/projects
   *
   * @param projectCreateParam create project param
   * @return true for success,otherwise false
   */
  public ProjectInfoResp create(ProjectCreateParam projectCreateParam) {
    String url = dolphinAddress + "/projects";
    try {
      HttpRestResult<ProjectInfoResp> result =
          dolphinsRestTemplate.postForm(
              url, getHeader(), projectCreateParam, ProjectInfoResp.class);
      if (result.getSuccess()) {
        return result.getData();
      } else {
        log.error("create project response:{}", result);
        throw new DolphinException("create dolphin scheduler project fail");
      }
    } catch (Exception e) {
      throw new DolphinException("create dolphin scheduler project fail", e);
    }
  }

  /**
   * update project, api：/dolphinscheduler/projects/{code}
   *
   * @param projectUpdateParam update project param
   * @return true for success,otherwise false
   */
  public Boolean update(ProjectUpdateParam projectUpdateParam) {
    String url = dolphinAddress + "/projects/" + projectUpdateParam.getProjectCode();
    try {
      HttpRestResult<String> result =
          dolphinsRestTemplate.putForm(url, getHeader(), projectUpdateParam, String.class);
      log.info("update project response:{}", result);
      return result.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("update dolphin scheduler project fail", e);
    }
  }

  /**
   * delete dolphin scheduler project
   *
   * @param projectCode dolphin scheduler project code
   * @return true for success,otherwise false
   */
  public Boolean delete(Long projectCode) {
    String url = dolphinAddress + "/projects/" + projectCode;
    try {
      HttpRestResult<String> result =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      log.info("delete project response:{}", result);
      return result.getSuccess();
    } catch (Exception e) {
      throw new DolphinException("delete dolphin scheduler project fail", e);
    }
  }

  /**
   * page query project list ，api:/dolphinscheduler/projects
   *
   * @param page page number
   * @param size page size
   * @param projectName project's name query criteria
   * @return {@link List<ProjectInfoResp>}
   */
  public List<ProjectInfoResp> page(Integer page, Integer size, String projectName) {

    page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
    size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

    String url = dolphinAddress + "/projects";
    Query query =
        new Query()
            .addParam("pageNo", String.valueOf(page))
            .addParam("pageSize", String.valueOf(size))
            .addParam("searchVal", projectName)
            .build();
    try {
      HttpRestResult<JsonNode> stringHttpRestResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);
      return JacksonUtils.parseObject(
              stringHttpRestResult.getData().toString(),
              new TypeReference<PageInfo<ProjectInfoResp>>() {})
          .getTotalList();
    } catch (Exception e) {
      throw new DolphinException("list dolphin scheduler project fail", e);
    }
  }
}
