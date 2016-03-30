package com.ithaca.user;

import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by David on 3/28/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void allTest() {
        when(userRepository.findAll()).thenReturn(new ArrayList<User>());
        Assert.assertEquals(0, userService.all().size());

        List<User> users = new ArrayList<>();
        users.add(new User("one", "password1"));
        users.add(new User("two", "password2"));

        when(userRepository.findAll()).thenReturn(users);

        Assert.assertEquals(2, userService.all().size());
        Assert.assertEquals("two", userService.all().get(1).getName());
    }

    @Test
    public void createTest() {
        User saved = new User("four", "password4");
        when(userRepository.findByName(saved.getName())).thenReturn(saved);

        Assert.assertNull(userService.create(saved.getName(), saved.getPassword(), "question", "answer"));
    }

    @Test
    public void findTest() {
        User user = new User("five", "password5");
        when(userRepository.findOne((long) 1)).thenReturn(user);

        Assert.assertEquals("five", userService.find((long) 1).getName());
    }

    @Test
    public void checkValidTest() {
        when(userRepository.findByName("user2")).thenReturn(new User("user2", "$2a$12$yJHCPO5jhCVO0m3jZICwoe1k9wq2ADDIq6raEXa88CKrQ8yXrke6u"));

        Assert.assertNull(userService.checkValid("user1", "pass1"));
        Assert.assertEquals("user2", userService.checkValid("user2", "pass2").getName());
    }
}
