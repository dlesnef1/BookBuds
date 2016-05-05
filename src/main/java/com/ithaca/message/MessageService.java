package com.ithaca.message;

public interface MessageService {

    /**
     *
     * @param userId the id of the user that would like to view their messages
     * @param otherUserName the name of the other user
     * @return The thread between the two users.
     */
    Thread findThread(Long userId, String otherUserName);

    /**
     *
     * @param userId The id of the user sending the message
     * @param recipientName The name of the user being messaged.
     * @param text The text of the message.
     * @return The thread between the two users.
     */
    Thread create(Long userId, String recipientName, String text);

    /**
     *
     * @param userId The id of the user editing their comment.
     * @param messageId The id of the message being edited.
     * @param text The new text for the message.
     * @return The thread between the two users.
     */
    Thread edit(Long userId, Long messageId, String text);

    /**
     *
     * @param userId The id of the user deleting the message.
     * @param messageId The id of the message between deleted.
     * @return True if the message is deleted, false otherwise.
     */
    Boolean delete(Long userId, Long messageId);
}
