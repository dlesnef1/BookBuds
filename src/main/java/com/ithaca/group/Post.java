package com.ithaca.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String text;

    @NotNull
    private String created;

    public Post() {
    }

    public Post(User user, Book_Group bookGroup, String text, String created) {
        this.bookGroup = bookGroup;
        this.user = user;
        this.text = text;
        this.created = created;
    }

    @JsonIgnore
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
