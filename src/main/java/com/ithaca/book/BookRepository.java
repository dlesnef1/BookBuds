package com.ithaca.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The book repository which allows for saving of books to the database.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
}
