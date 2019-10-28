package com.banking.opb.api;

import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.service.ILoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

    @Autowired
    private ILoginService ILoginServiceImpl;
    private UserLoginInformation testUser = null;

    @Before
    public void init() {
        testUser = new UserLoginInformation();
        testUser.setUsername("testUsername");
        testUser.setPassword("test@123".toCharArray());
        testUser.setEmail("test@test.comS");
    }

    @Test
    @Order(1)
    public void signUpUserFirstTest() {
        String name = ILoginServiceImpl.singedUpUser(testUser);
        Assert.assertTrue(!"MandatoryMissing".equals(name) && !"UserExists".equals(name));
    }

    @Test
    @Order(2)
    public void signUpUserSecondTest() {
        String name = ILoginServiceImpl.singedUpUser(testUser);
        System.out.println(name);
        Assert.assertTrue("UserExists".equals(name));
    }
}
