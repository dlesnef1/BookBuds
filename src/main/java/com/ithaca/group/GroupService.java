package com.ithaca.group;

/**
 * Created by David on 4/28/16.
 */
public interface GroupService {

    Book_Group create(Long userId, Long bookId);

    Book_Group join(Long userId, Long groupId);
}
