package com.ithaca.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.group.Book_Group;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/19/16.
 */

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String isbn;

    private String imageUrl;

    @OneToMany(mappedBy = "book")
    private List<Book_Group> book_groups;

    public Book() {
    }

    public Book(String title, String author, String isbn, String imageUrl) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
        this.book_groups = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonIgnore
    public List<Book_Group> getBook_groups() {
        return book_groups;
    }

    public void setBook_groups(List<Book_Group> book_groups) {
        this.book_groups = book_groups;
    }
}
