package com.ithaca.group;

/**
 * The service layer for groups. Provides methods that relate to making/joining and posting to groups.
 */
public interface GroupService {

    /**
     * @param userId The id of the user currently attempting to make a book group.
     * @param bookId The id of the book the user wishes to make a group for.
     * @return The created book group, or null if the user or book id's return null from the DB.
     */
    Book_Group create(Long userId, Long bookId);

    /**
     * @param userId  The id of the user currently attempting to join a book group.
     * @param groupId The id of the group the user wishes to join.
     * @return The book group with the user now in it. Null if either the user or group doesn't exist.
     */
    Book_Group join(Long userId, Long groupId);

    /**
     * @param userId  The id of the user wishing to view the book group.
     * @param groupId The id of the book group the user wishes to view.
     * @return The book group or null if the user or group do not exist.
     */
    Book_Group find(Long userId, Long groupId);

    /**
     * @param userId  The id of the user that wishes to post to the group
     * @param groupId The id of the group the user wants to post too.
     * @param text    The message the user would like to post.
     * @return The book group with the newly added post, or null if the user/group are null or if the user isn't in the group.
     */
    Book_Group post(Long userId, Long groupId, String text);

    /**
     * @param userId  The id of the current user wishing to reply to a post.
     * @param groupId The id of the group that the post the user wishes to reply too is in.
     * @param postId  The id of the post the user wishes to reply too.
     * @param text    The reply message that will be a child comment to the original post.
     * @return The book group with the newly added reply to a post. Null if the user/group/post are null or if the user isn't in the group.
     */
    Book_Group reply(Long userId, Long groupId, Long postId, String text);

    /**
     * @param userId  The id of the current user wishing to edit a post of theirs.
     * @param groupId The id of the group that the post the user wishes to edit is in.
     * @param postId  The id of the post the user wishes to edit.
     * @param text    The text the user wishes to change the post too.
     * @return The book group with the edit to the post. Null if the user/group/post are null or if the user isn't in the group.
     */
    Book_Group edit(Long userId, Long groupId, Long postId, String text);

    /**
     * @param userId  The id of the user that wishes to upvote a post.
     * @param groupId The id of the group that contains the post the user wants to upvote.
     * @param postId  The id of the post the user wants to upvote.
     * @return The book group with the post having its score increased by one.
     */
    Book_Group upvote(Long userId, Long groupId, Long postId);

    /**
     * @param userId  The id of the user that wishes to report a post.
     * @param groupId The id of the group that contains the post being reported.
     * @param postId  The id of the post being reported.
     * @return The book group with the reported post.
     */
    Book_Group report(Long userId, Long groupId, Long postId);
}
