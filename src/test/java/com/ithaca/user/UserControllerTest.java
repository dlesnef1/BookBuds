package com.ithaca.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by David on 3/24/2016.
 */

public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testAll() {
        Assert.assertNotNull(userController.all());
    }

    @Test
    public void testFind()throws Exception {
        Assert.assertNotNull(userController.find((long) 1));
    }

    @Test
    public void testCreate() {
        Assert.assertNotNull(userController.create("David", "password"));
    }
}
