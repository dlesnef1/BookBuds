package com.ithaca.message;

import com.ithaca.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class MessageTest {

    @Test
    public void testMessage() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        User user = new User("David", "password", "q", "a");
        Message message = new Message(user, new Thread(), "This is my message", timeStamp);

        Assert.assertEquals("This is my message", message.getText());
        Assert.assertEquals(user, message.getUser());

        message.setText("New message");
        Assert.assertEquals("New message", message.getText());

        Assert.assertNotNull(message.getCreated());
        Assert.assertEquals("David", message.getUsername());

        Message message1 = new Message();
        Assert.assertEquals(null, message1.getText());
    }
}
