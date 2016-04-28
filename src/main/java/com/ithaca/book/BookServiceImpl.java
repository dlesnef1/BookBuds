package com.ithaca.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 4/28/16.
 */

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookSearch bookSearch;

    @Override
    public List<Book> all() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public List<Book> search(String title) {
        return bookSearch.search(title);
    }
}
