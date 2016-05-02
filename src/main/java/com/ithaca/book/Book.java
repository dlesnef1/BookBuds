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
    @Column(columnDefinition="text")
    private String title;

    @NotNull
    @Column(columnDefinition="text")
    private String author;

    @NotNull
    private String publisher;

    @NotNull
    private String isbn;

    @NotNull
    private String date;

    @Column(columnDefinition="text")
    private String imageUrl;

    @Column(columnDefinition="text")
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Book_Group> book_groups;

    public Book() {
    }

    public Book(String title, String author, String publisher, String isbn, String date, String imageUrl, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.date = date;
        this.imageUrl = imageUrl;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public List<Book_Group> getBook_groups() {
        return book_groups;
    }

    public void setBook_groups(List<Book_Group> book_groups) {
        this.book_groups = book_groups;
    }
}
