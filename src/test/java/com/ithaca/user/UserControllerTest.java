package com.ithaca.user;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock private UserServiceImpl userService;
    @InjectMocks private UserController userController;

    @Before
    public void init() {
        List<User> users = new ArrayList<>();
        users.add(new User("one", "password1"));
        users.add(new User("two", "password2"));

        when(userService.all()).thenReturn(users);
        when(userService.find((long) 1)).thenReturn(new User("three", "hashed3"));
        when(userService.create("four", "password4")).thenReturn(new User("four", "hashed4"));
    }

    @Test
    public void allTest() {
        List<User> all = userController.all();

        Assert.assertEquals(2, all.size());
        Assert.assertEquals("one", all.get(0).getName());
        Assert.assertEquals("password1", all.get(0).getPassword());
        Assert.assertEquals("two", all.get(1).getName());
        Assert.assertEquals("password2", all.get(1).getPassword());
    }

    @Test
    public void findTest() {
        User user = userController.find((long) 1);

        Assert.assertNotNull(user);
        Assert.assertEquals("three", user.getName());
        Assert.assertEquals("hashed3", user.getPassword());
    }

    @Test
    public void createTest() {
        User user = userController.create("four", "password4");

        Assert.assertNotNull(user);
        Assert.assertEquals("four", user.getName());
        Assert.assertEquals("hashed4", user.getPassword());
    }
}
