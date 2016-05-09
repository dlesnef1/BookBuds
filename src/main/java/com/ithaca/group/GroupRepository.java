package com.ithaca.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The group repository which allows for saving of groups to the database.
 */
@Repository
public interface GroupRepository extends CrudRepository<Book_Group, Long> {
}
