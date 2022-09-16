package com.github.weaksloth.dolphins.resource;

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

// TODO support upload file
@Slf4j
public class ResourceOperator extends AbstractOperator {

  public ResourceOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * page query resource list
   *
   * @param pid pid
   * @param fileName file name
   * @return {@link List <ResourceQueryRes>}
   */
  public List<ResourceQueryRes> page(Integer page, Integer size, String pid, String fileName) {

    page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
    size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

    String url = dolphinAddress + "/resources";
    Query query =
        new Query()
            .addParam("type", DolphinClientConstant.Resource.TYPE_FILE)
            .addParam("pageNo", String.valueOf(page))
            .addParam("pageSize", String.valueOf(size))
            .addParam("searchVal", fileName)
            .addParam("id", pid);
    try {
      HttpRestResult<JsonNode> restResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

      return JacksonUtils.parseObject(
              restResult.getData().toString(), new TypeReference<PageInfo<ResourceQueryRes>>() {})
          .getTotalList();
    } catch (Exception e) {
      log.error(DolphinException.LIST_RESOURCE_ERROR, e);
      throw new DolphinException(DolphinException.LIST_RESOURCE_ERROR);
    }
  }

  /**
   * online create resource/file
   *
   * @param resourceCreateParam online create file param
   * @return true for success,otherwise false
   */
  public Boolean onlineCreate(ResourceCreateParam resourceCreateParam) {
    String url = dolphinAddress + "/resources/online-create";
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.postForm(url, getHeader(), resourceCreateParam, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      log.error(DolphinException.ONLINE_CREATE_RESOURCE_ERROR, e);
      throw new DolphinException(DolphinException.ONLINE_CREATE_RESOURCE_ERROR);
    }
  }

  /**
   * online update resource/file
   *
   * @param resourceUpdateParam online update resource param
   * @return true for success,otherwise false
   */
  public Boolean onlineUpdate(ResourceUpdateParam resourceUpdateParam) {
    String url = dolphinAddress + "/resources/" + resourceUpdateParam.getId() + "/update-content";
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.putForm(url, getHeader(), resourceUpdateParam, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      log.error(DolphinException.ONLINE_UPDATE_RESOURCE_ERROR, e);
      throw new DolphinException(DolphinException.ONLINE_UPDATE_RESOURCE_ERROR);
    }
  }

  /**
   * delete resource by id
   *
   * @param id resource id
   * @return
   */
  public Boolean delete(Long id) {
    String url = dolphinAddress + "/resources/" + id;
    try {
      HttpRestResult<String> restResult =
          dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
      return restResult.getSuccess();
    } catch (Exception e) {
      log.error(DolphinException.DELETE_RESOURCE_ERROR, e);
      throw new DolphinException(DolphinException.DELETE_RESOURCE_ERROR);
    }
  }
}
