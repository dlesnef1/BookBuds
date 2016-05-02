package com.ithaca.group;

import com.ithaca.book.Book;
import com.ithaca.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/19/16.
 */

@Entity
public class Book_Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="bookGroup")
    private List<Post> posts;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_group_user", joinColumns=@JoinColumn(name="book_group_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    public Book_Group() {
    }

    public Book_Group(Book book) {
        this.book = book;
        this.posts = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
