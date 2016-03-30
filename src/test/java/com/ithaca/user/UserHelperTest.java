package com.ithaca.user;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by David on 3/29/16.
 */
public class UserHelperTest {

    @Test
    public void generateTokenTest() {

        UserHelper userHelper = new UserHelper();
        Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSJ9.RPTbu-xzGgLwcwsyN8wQNn5FpEWjJ_YvzP1gBb3m-Q4",
                userHelper.generateToken(new User("user1", "pass1", "question", "answer")).get("token"));
    }
}
