package com.ithaca.book;

import com.ithaca.group.Book_Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller that provides end points for books. This is not for book groups, this is for the actual books stored in the DB.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    /**
     *
     * @param title The name of the book the user would like to search for.
     * @return A list of ten books that closely match the searched for title.
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Book> search(@RequestParam String title) {
        return bookService.search(title);
    }

    /**
     *
     * @param id The id of the book the user wishes to return.
     * @return The actual Book of the associated id.
     */
    @RequestMapping("/{id}")
    public Book findBook(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        return book;
    }

    /**
     *
     * @param id The id of the book the user wishes to find the already-created groups for.
     * @return A list of groups already created for the book.
     */
    @RequestMapping("/{id}/groups")
    public List<Book_Group> findGroups(@PathVariable Long id) {
        return bookService.findGroups(id);
    }
}
