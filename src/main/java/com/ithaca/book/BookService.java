package com.ithaca.book;

import com.ithaca.group.Book_Group;

import java.util.List;

/**
 * The service for books. Provides methods to search for a book, find a book by id, and the groups for a particular book.
 */
public interface BookService {

    /**
     * @param title The title of the book the user wishes to search for.
     * @return A list of 10 books that closely match the title being searched for.
     */
    List<Book> search(String title);

    /**
     * @param id The id of the book being searched for.
     * @return The book that corresponds to the id the user was looking for.
     */
    Book findBook(Long id);

    /**
     * @param id The id of the book the user wants to get all of the groups for.
     * @return A list of all of the groups for the book being looked at.
     */
    List<Book_Group> findGroups(Long id);
}
