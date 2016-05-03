package com.ithaca.user;

import com.ithaca.group.Book_Group;
import com.ithaca.message.Thread;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 3/25/16.
 */
public interface UserService {

    User find(Long id);

    List<Book_Group> findGroups(Long userId);

    List<Thread> findThreads(Long userId);

    User create(String name, String password, String question, String answer);

    User checkValid(String name, String password);

    Map<String, String> showQuestion(String name);

    User changePassword(String name, String newPassword, String answer);
}
