package com.ithaca.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Matchers.*;
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
    public void createTestPass() {
        User unsaved = new User("three", "password3");
        when(userRepository.findByName(unsaved.getName())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(unsaved);

        System.out.println(userService.create(unsaved.getName(),unsaved.getPassword()));
    }

    @Test
    public void createTestFail() {
        User saved = new User("four", "password4");
        when(userRepository.findByName(saved.getName())).thenReturn(saved);

        Assert.assertNull(userService.create(saved.getName(), saved.getPassword()));
    }

    @Test
    public void findTest() {
        User user = new User("five", "password5");
        when(userRepository.findOne((long) 1)).thenReturn(user);

        Assert.assertEquals("five", userService.find((long) 1).getName());
    }
}
