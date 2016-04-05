package com.ithaca.message;

/**
 * Created by David on 4/5/16.
 */
public interface MessageService {

    // Find the thread between user and otherUser (a thread is the list of messages between the two)
    Thread findThread(Long userId, String otherUserName);

    // Create a new message for a thread between the two users, if thread doesn't exist, make it.
    Thread create(Long userId, String recipientName, String text);
}
