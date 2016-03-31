package com.ithaca.user;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 3/25/16.
 */
public interface UserService {

    List<User> all();

    User find(Long id);

    User create(String name, String password, String question, String answer);

    User checkValid(String name, String password);

    Map<String, String> showQuestion(String name);

    User changePassword(String name, String newPassword, String answer);
}
