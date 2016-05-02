package com.ithaca.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 4/28/16.
 */

@Repository
public interface GroupRepository extends CrudRepository<Book_Group, Long>{
}
