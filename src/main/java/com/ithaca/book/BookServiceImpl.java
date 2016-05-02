package com.ithaca.book;

import com.ithaca.group.Book_Group;
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
    public List<Book> search(String title) {
        return bookSearch.search(title);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book_Group> findGroups(Long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            return null;
        }

        return book.getBook_groups();
    }
}
