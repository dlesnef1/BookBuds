package com.ithaca.message;

import com.ithaca.user.User;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by David on 4/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    @Mock
    MessageServiceImpl messageService;

    @InjectMocks
    MessageController messageController;

    private Thread thread;
    private HttpServletRequest request;

    @Before
    public void init() {
        thread = new Thread();
        User user = new User("David", "pass");
        thread.getUsers().add(user);
        thread.getUsers().add(new User("Nick", "pass"));
        thread.getMessages().add(new Message(user, thread, "Message to Nick", "time"));

        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer one = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(one);
        when(messageService.findThread(one.longValue(), "Nick")).thenReturn(thread);
        when(messageService.create(one.longValue(), "Nick", "Another message to Nick")).thenReturn(new Thread());

        when(messageService.delete(1L, 1L)).thenReturn(Boolean.TRUE);
    }

    @Test
    public void testFindThread() {
        Assert.assertEquals(1 ,messageController.findThread(request, "Nick").getMessages().size());
        Assert.assertEquals("Message to Nick" ,messageController.findThread(request, "Nick").getMessages().get(0).getText());
    }

    @Test
    public void testCreate() {
        Assert.assertEquals(0 ,messageController.create(request, "Nick", "Another message to Nick").getMessages().size());
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(messageController.delete(request, 1L));
    }
}
