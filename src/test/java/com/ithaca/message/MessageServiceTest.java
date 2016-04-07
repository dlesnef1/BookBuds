package com.ithaca.message;

import com.ithaca.user.User;
import com.ithaca.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by David on 4/5/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private ThreadRepository threadRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    public void findThreadTest() {
        Long one = 1L;
        User user1 = new User("David", "pass");
        User user2 = new User("Nick", "pass");
        Thread thread = new Thread();
        thread.getUsers().add(user1);
        thread.getUsers().add(user2);
        Message message = new Message(user1, thread, "Message to Nick", "time");
        thread.getMessages().add(message);
        user1.getThreads().add(thread);

        when(userRepository.findOne(one)).thenReturn(user1);
        when(userRepository.findByName("Nick")).thenReturn(user2);

        Assert.assertTrue(messageService.findThread(one, "Nick").getUsers().contains(user1));
        Assert.assertTrue(messageService.findThread(one, "Nick").getUsers().contains(user2));

        // test return null
        when(userRepository.findByName("Nick")).thenReturn(user1);
        Assert.assertNull(messageService.findThread(one, "Nick"));
    }

    @Test
    public void createTest() {
        Long one = 1L;
        User user1 = new User("David", "pass");
        User user2 = new User("Nick", "pass");
        Thread thread = new Thread();
        thread.getUsers().add(user1);
        thread.getUsers().add(user2);
        Message message = new Message(user1, thread, "Message to Nick", "time");
        thread.getMessages().add(message);
        user1.getThreads().add(thread);

        when(userRepository.findOne(one)).thenReturn(user1);
        when(userRepository.findByName("Nick")).thenReturn(user2);
        when(threadRepository.save(thread)).thenReturn(thread);

        Assert.assertTrue(messageService.create(one, "Nick", "A message to Nick").getUsers().contains(user1));
    }
}
