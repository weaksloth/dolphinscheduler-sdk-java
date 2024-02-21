package com.github.weaksloth.dolphins.resource;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ResourceTest extends BaseTest {

  private final String fileName = "dophinsdk-create2";
  private final String suffix = "sh";
  private final String fullName =
      "file:/home/"
          + tenantCode
          + "/ds/upload/"
          + tenantCode
          + "/resources/"
          + fileName
          + "."
          + suffix;

  @Test
  public void testPage() {
    List<ResourceQueryRes> list =
        getClient()
            .opsForResource()
            .page(null, null, DolphinClientConstant.Resource.DEFAULT_PID_FILE, "");
    list.forEach(System.out::println);
  }

  @Test
  public void testOnlineCreate() {
    ResourceCreateParam resourceCreateParam = new ResourceCreateParam();
    resourceCreateParam
        .setSuffix(suffix)
        .setFileName(fileName)
        .setContent("created by dolphin scheduler java sdk");
    Assert.assertTrue(getClient().opsForResource().onlineCreate(resourceCreateParam));
  }

  @Test
  public void testOnlineUpdate() {
    ResourceUpdateParam resourceUpdateParam = new ResourceUpdateParam();
    resourceUpdateParam
        .setTenantCode(tenantCode)
        .setFullName(fullName)
        .setContent("update by dolphin scheduler java sdk");
    Assert.assertTrue(getClient().opsForResource().onlineUpdate(resourceUpdateParam));
  }

  @Test
  public void delete() {
    Assert.assertTrue(getClient().opsForResource().delete(tenantCode, fullName));
  }
}
