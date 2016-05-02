package com.ithaca.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 4/27/16.
 */

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
}
