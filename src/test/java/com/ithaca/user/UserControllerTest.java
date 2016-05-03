package com.ithaca.user;


import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock private UserServiceImpl userService;
    @Mock private UserHelper userHelper;
    @InjectMocks private UserController userController;

    @Test
    public void findTest() {

        HttpServletRequest request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer one = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(one);
        when(userService.find(one.longValue())).thenReturn(new User("user3", "pass3"));


        Assert.assertEquals("user3", userController.find(request).getName());
    }

    @Test
    public void createTest() {
        User user = new User("user4", "pass4");
        when(userService.create("user4", "pass4", "question4", "answer4")).thenReturn(user);
        when(userService.create("user5", "pass5", "question5", "answer5")).thenReturn(null);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", "mockToken");
        when(userHelper.generateToken(user)).thenReturn(tokenMap);

        Assert.assertNull(userController.create("user5", "pass5", "question5", "answer5"));
        Assert.assertEquals("mockToken", userController.create("user4", "pass4", "question4", "answer4").get("token"));
    }

    @Test
    public void loginTest() {
        User user = new User("user6", "pass6");
        when(userService.checkValid("user6", "pass6")).thenReturn(user);
        when(userService.checkValid("user7", "pass7")).thenReturn(null);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", "mockToken");
        when(userHelper.generateToken(user)).thenReturn(tokenMap);

        Assert.assertNull(userController.login("user7", "pass7"));
        Assert.assertEquals("mockToken", userController.login("user6", "pass6").get("token"));
    }

    @Test
    public void showPasswordTest() {
        Map<String, String> question = new HashMap<>();
        question.put("question", "Favorite color?");
        when(userService.showQuestion("user")).thenReturn(question);

        Assert.assertEquals("Favorite color?", userController.showQuestion("user").get("question"));
    }
}
