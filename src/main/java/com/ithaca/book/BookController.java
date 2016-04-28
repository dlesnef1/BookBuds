package com.ithaca.book;

import com.ithaca.group.Book_Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David on 4/25/16.
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(method = RequestMethod.POST)
    public List<Book> search(@RequestParam String title) {
        return bookService.search(title);
    }

    @RequestMapping("/{id}")
    public List<Book_Group> findGroups(@PathVariable Long id) {
        return bookService.findGroups(id);
    }
}
