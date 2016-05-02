package com.ithaca.book;

import com.ithaca.group.Book_Group;

import java.util.List;

/**
 * Created by David on 4/28/16.
 */
public interface BookService {

    List<Book> search(String title);

    Book findBook(Long id);

    List<Book_Group> findGroups(Long id);
}
