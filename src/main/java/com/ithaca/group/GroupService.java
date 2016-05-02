package com.ithaca.group;

/**
 * Created by David on 4/28/16.
 */
public interface GroupService {

    Book_Group create(Long userId, Long bookId);

    Book_Group join(Long userId, Long groupId);

    Book_Group find(Long userId, Long groupId);

    Book_Group post(Long userId, Long groupId, String text);

    Book_Group reply(Long userId, Long groupId, Long postId, String text);

    Book_Group edit(Long userId, Long groupId, Long postId, String text);

    Book_Group upvote(Long userId, Long groupId, Long postId);
}
