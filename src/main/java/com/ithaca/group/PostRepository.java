package com.ithaca.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 4/29/16.
 */

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
}
