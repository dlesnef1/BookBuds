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


    @RequestMapping()
    public List<User> all() {
        return new ArrayList<>();
    }


    public User find(Long id) {
        return null;
    }


    public User create(String name, String password) {
        return null;
    }
}
