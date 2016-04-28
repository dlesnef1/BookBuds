package com.ithaca.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by David on 4/25/16.
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping
    public List<Book> all() {
        return bookService.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Book> search(@RequestParam String title) {
        return bookService.search(title);
    }
}
