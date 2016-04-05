package com.ithaca.message;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by David on 4/4/16.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
