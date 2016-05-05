package com.ithaca.message;

import org.springframework.data.repository.CrudRepository;


/**
 * The repository that stores messages in the database.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
