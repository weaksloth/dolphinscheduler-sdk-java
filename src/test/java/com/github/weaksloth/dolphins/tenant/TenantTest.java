package com.github.weaksloth.dolphins.tenant;

import com.github.weaksloth.dolphins.BaseTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TenantTest extends BaseTest {

  public static final String TENANT_CODE = "xcchen";

  @Test
  public void testCreateTenant() {
    TenantDefineParam createParam = new TenantDefineParam();
    createParam.setTenantCode(TENANT_CODE).setDescription("create by dolphin scheduler api");
    TenantInfoResp tenantInfoResp = getClient().opsForTenant().create(createParam);
    System.out.println(tenantInfoResp);
    Assert.assertEquals(TENANT_CODE, tenantInfoResp.getTenantCode());
  }

  @Test
  public void testUpdateTenant() {
    long tenantId = getClient().opsForTenant().page(null, null, TENANT_CODE).get(0).getId();
    TenantDefineParam updateParam = new TenantDefineParam();
    updateParam.setTenantCode(TENANT_CODE).setDescription("update by dolphin scheduler");
    Assert.assertTrue(getClient().opsForTenant().update(tenantId, updateParam));
  }

  @Test
  public void testPageTenant() {
    List<TenantInfoResp> page = getClient().opsForTenant().page(null, null, "");
    page.forEach(System.out::println);
  }

  @Test
  public void testDeleteTenant() {
    long tenantId = getClient().opsForTenant().page(null, null, TENANT_CODE).get(0).getId();
    Assert.assertTrue(getClient().opsForTenant().delete(tenantId));
  }
}
