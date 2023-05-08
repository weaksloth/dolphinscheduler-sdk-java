package com.github.weaksloth.dolphins.project;

import com.github.weaksloth.dolphins.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ProjectTest extends BaseTest {

  private static final String PROJECT_NAME = "test_project";

  @Test
  public void testCreateProject() {

    ProjectCreateParam param = new ProjectCreateParam();
    param.setProjectName(PROJECT_NAME).setDescription("created by dolphinscheduler java sdk");
    ProjectInfoResp projectInfoResp = getClient().opsForProject().create(param);
    System.out.println(projectInfoResp);
    Assert.assertEquals(PROJECT_NAME, projectInfoResp.getName());
  }

  @Test
  public void testListProject() {
    getClient().opsForProject().page(null, null, null).forEach(System.out::println);
  }

  @Test
  public void testUpdateProject() {
    ProjectInfoResp projectInfo = getClient().opsForProject().page(null, null, PROJECT_NAME).get(0);
    ProjectUpdateParam updateParam = new ProjectUpdateParam();
    String newDescription = "updated by dolphinscheduler java sdk";
    updateParam
        .setProjectName(PROJECT_NAME)
        .setProjectCode(projectInfo.getCode())
        .setUserName(projectInfo.getUserName())
        .setDescription(newDescription);
    ProjectInfoResp newProjectInfo = getClient().opsForProject().update(updateParam);
    Assert.assertEquals(newDescription, newProjectInfo.getDescription());
  }

  @Test
  public void testDeleteProject() {
    // get test project code
    long code = getClient().opsForProject().page(null, null, PROJECT_NAME).get(0).getCode();
    getClient().opsForProject().delete(code);
  }
}
