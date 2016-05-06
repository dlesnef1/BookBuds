package com.ithaca.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createTest() {
        User saved = new User("four", "password4", "q", "a");
        when(userRepository.findByName(saved.getName())).thenReturn(saved);

        Assert.assertNull(userService.create(saved.getName(), saved.getPassword(), "question", "answer"));
    }

    @Test
    public void findTest() {
        User user = new User("five", "password5", "q", "a");
        when(userRepository.findOne((long) 1)).thenReturn(user);

        Assert.assertEquals("five", userService.find((long) 1).getName());
    }

    @Test
    public void checkValidTest() {
        when(userRepository.findByName("user2")).thenReturn(new User("user2", "$2a$12$yJHCPO5jhCVO0m3jZICwoe1k9wq2ADDIq6raEXa88CKrQ8yXrke6u", "q", "a"));

        Assert.assertNull(userService.checkValid("user1", "pass1"));
        Assert.assertEquals("user2", userService.checkValid("user2", "pass2").getName());
    }

    @Test
    public void changePasswordTest() {
        User user = new User("user3", "$2a$12$yJHCPO5jhCVO0m3jZICwoe1k9wq2ADDIq6raEXa88CKrQ8yXrke7u", "question", "$2a$12$hVesoCjSYL8KiWDm1fbHDu.TO.wTAgeyl2x0gkouPs466t.Gd0JjC");

        when(userRepository.findByName("user3")).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(new User("user3", "pass3", "q", "a"));

        Assert.assertNull(userService.checkValid("user1", "pass1"));
        Assert.assertEquals("pass3", userService.changePassword("user3", "pass3", "Rose").getPassword());
    }

    @Test
    public void showQuestionTest() {

        when(userRepository.findByName("user")).thenReturn(new User("user", "password", "Favorite color?", "green"));

        Assert.assertNull(userService.showQuestion("user2"));
        Assert.assertEquals("Favorite color?", userService.showQuestion("user").get("question"));
    }
}
