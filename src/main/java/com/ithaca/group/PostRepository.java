package com.ithaca.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository used to store posts to the database.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
}
