package com.ithaca.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/25/16.
 */

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> all() {

        List<User> users = new ArrayList<>();
        users.add(new User("David", "password"));
        users.add(new User("Nick", "password2"));

        return users;
    }

    @Override
    public User find(Long id) {
        return new User("Shelby", "password3");
    }

    @Override
    public User create(String name, String password) {
        return new User(name, password);
    }
}
