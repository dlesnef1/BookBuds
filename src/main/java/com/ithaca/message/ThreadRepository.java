package com.ithaca.message;

import org.springframework.data.repository.CrudRepository;

/**
 * The thread repository, responsible for saving threads to the database.
 */
public interface ThreadRepository extends CrudRepository<Thread, Long> {
}
