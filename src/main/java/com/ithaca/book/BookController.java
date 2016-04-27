package com.ithaca.book;

import com.ithaca.group.Book_Group;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David on 4/25/16.
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping
    public Book_Group all() {
        return null;
    }
}
