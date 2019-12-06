package com.banking.opb.api;

import com.banking.opb.domain.custom.Card;
import com.banking.opb.service.ICardService;
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
public class CardTest {

    @Autowired
    private ICardService ICardServiceImpl;
    private Card testCard = null;

    @Before
    public void init() { 
        testCard = new Card ("1234 5678 9123 4567", "Test Card", "12/12","555");
    }

    @Test
    @Order(1)
    public void addCardTest() {
        String response = ICardServiceImpl.addCard(testCard);
        Assert.assertTrue("Success".equals(response));
    }

    /*@Test
    @Order(2)
    public void signUpUserSecondTest() {
        String name = ICardServiceImpl.singedUpUser(testCard);
        System.out.println(name);
        Assert.assertTrue("UserExists".equals(name));
    }*/
}
