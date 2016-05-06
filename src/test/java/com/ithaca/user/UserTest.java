package com.ithaca.user;

import com.ithaca.message.Message;
import com.ithaca.message.Thread;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.when;

public class UserTest {

    @Test
    public void testUser() {
        // Make a user with no construction values
        User userEmpty = new User();
        Assert.assertNotNull(userEmpty);

        // Make a user with test data
        User user = new User("David", "password", "Mother's maiden name?", "Rose");
        Assert.assertNotNull(user);

        Assert.assertEquals("David", user.getName());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("Mother's maiden name?", user.getSecurityQuestion());
        Assert.assertEquals("Rose", user.getSecurityAnswer());

        user.setName("David Lesnefsky");
        Assert.assertEquals("David Lesnefsky", user.getName());

        user.setPassword("password1");
        Assert.assertEquals("password1", user.getPassword());

        user.setSecurityQuestion("Favorite food?");
        Assert.assertEquals("Favorite food?", user.getSecurityQuestion());

        user.setSecurityAnswer("pizza");
        Assert.assertEquals("pizza", user.getSecurityAnswer());

        user.getMessages().add(new Message(user, new Thread(), "message", "time"));
        Assert.assertEquals(1, user.getMessages().size());
    }
}
