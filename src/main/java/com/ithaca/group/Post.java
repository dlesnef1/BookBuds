package com.ithaca.group;

import com.ithaca.book.Book;
import com.ithaca.user.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David on 4/25/16.
 */

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book_Group bookGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Post() {
    }

    public Post(User user, Book_Group bookGroup) {
        this.bookGroup = bookGroup;
        this.user = user;
    }

    public Book_Group getBookGroup() {
        return bookGroup;
    }

    public void setBookGroup(Book_Group bookGroup) {
        this.bookGroup = bookGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
