package com.banking.opb.user;

import com.banking.opb.AbstractTestSupport;
import com.banking.opb.clientapi.EntitlementsApiClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class EntitlementsTest extends AbstractTestSupport {

    @Value("${obp.username}")
    private String user;
    @Autowired private EntitlementsApiClient entitlementsApiClient;

    @Test
    public void entitlementsUser() throws Exception {
        String entz = entitlementsApiClient.getEntitlements(user);
        Assert.assertNotNull(entz);
    }
}
