package com.ithaca.message;

import com.ithaca.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * Created by David on 4/4/16.
 */
public class MessageTest {

    @Test
    public void testMessage() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        User user = new User("David", "password");
        Message message = new Message(user, new Thread(), "This is my message", timeStamp);

        Assert.assertEquals("This is my message", message.getText());
        Assert.assertEquals(user, message.getUser());

        message.setText("New message");
        Assert.assertEquals("New message", message.getText());

        Assert.assertNotNull(message.getCreated());
    }
}
