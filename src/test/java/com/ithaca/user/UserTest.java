package com.ithaca.user;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.when;

/**
 * Created by David on 3/24/16.
 */
public class UserTest {

    @Test
    public void testUser() {
        // Make a user with no construction values
        User userEmpty = new User();
        Assert.assertNotNull(userEmpty);

        // Make a user with test data
        User user = new User("David", "password");
        Assert.assertNotNull(user);

        Assert.assertEquals("David", user.getName());
        Assert.assertEquals("password", user.getPassword());

        user.setName("David Lesnefsky");
        Assert.assertEquals("David Lesnefsky", user.getName());

        user.setPassword("password1");
        Assert.assertEquals("password1", user.getPassword());
    }
}
