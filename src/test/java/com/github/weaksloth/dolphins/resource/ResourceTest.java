package com.github.weaksloth.dolphins.resource;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ResourceTest extends BaseTest {

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
        .setSuffix("sh")
        .setFileName("dophinsdk-create")
        .setContent("created by dolphin scheduler java sdk");
    Assert.assertTrue(getClient().opsForResource().onlineCreate(resourceCreateParam));
  }

  @Test
  public void testOnlineUpdate() {
    ResourceUpdateParam resourceUpdateParam = new ResourceUpdateParam();
    resourceUpdateParam.setId(401L).setContent("update by dolphin scheduler java sdk");
    Assert.assertTrue(getClient().opsForResource().onlineUpdate(resourceUpdateParam));
  }

  @Test
  public void delete() {
    Assert.assertTrue(getClient().opsForResource().delete(401L));
  }
}
