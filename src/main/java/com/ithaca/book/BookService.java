package com.ithaca.book;

import java.util.List;

/**
 * Created by David on 4/28/16.
 */
public interface BookService {

    List<Book> all();

    List<Book> search(String title);
}
