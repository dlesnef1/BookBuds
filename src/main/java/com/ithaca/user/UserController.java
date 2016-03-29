package com.ithaca.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/24/2016.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping
    public List<User> all() {
        return userService.all();
    }

    @RequestMapping("/{id}")
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(String name, String password) {
        return userService.create(name, password);
    }
}
