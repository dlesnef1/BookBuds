package com.ithaca.user;

import com.ithaca.group.Book_Group;
import com.ithaca.message.Thread;

import java.util.List;
import java.util.Map;

/**
 * Services for working with User entity.
 */
public interface UserService {

    /**
     *
     * @param id The id of the user wishing to be found
     * @return The user
     */
    User find(Long id);

    /**
     *
     * @param userId The id of the user that wishes to find their groups
     * @return a List of groups the user is in.
     */
    List<Book_Group> findGroups(Long userId);

    /**
     *
     * @param userId The id of the user wishing to find their threads
     * @return A list of threads the user is a part of.
     */
    List<Thread> findThreads(Long userId);

    /**
     *
     * @param name Name of user, must be unique to BookBuds
     * @param password Password for user
     * @param question Security question for user, used to change password
     * @param answer Security answer for user
     * @return A new user
     */
    User create(String name, String password, String question, String answer);

    /**
     *
     * @param name Name of user
     * @param password Password of user
     * @return The user if this is a valid user/password. Null otherwise.
     */
    User checkValid(String name, String password);

    /**
     *
     * @param name Name of the user who wishes to change their password
     * @return The question the user has saved, returns in JSON.
     */
    Map<String, String> showQuestion(String name);

    /**
     *
     * @param name Name of user wishing to change their password
     * @param newPassword The new password they want
     * @param answer The answer to the security question
     * @return The user, or null if invalid.
     */
    User changePassword(String name, String newPassword, String answer);
}
