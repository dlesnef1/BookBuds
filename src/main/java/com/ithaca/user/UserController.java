package com.ithaca.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/24/2016.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    // get all accounts
    @RequestMapping()
    public List<User> all() {
        return new ArrayList<>();
    }

    // get an account by id
    public User find(Long id) {
        return null;
    }

    // make a new account
    public User create(String name, String password) {
        return null;
    }
}
