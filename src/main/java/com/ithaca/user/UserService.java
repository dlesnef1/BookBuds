package com.ithaca.user;

import java.util.List;

/**
 * Created by David on 3/25/16.
 */
public interface UserService {

    List<User> all();

    User find(Long id);

    User create(String name, String password);

    User checkValid(String name, String password);
}
