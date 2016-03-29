package com.ithaca.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 3/24/16.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByName(String name);
}
