package com.ithaca.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.book.Book;
import com.ithaca.group.Book_Group;
import com.ithaca.group.Post;
import com.ithaca.message.Message;
import com.ithaca.message.Thread;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/24/16.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String securityQuestion;

    @NotNull
    private String securityAnswer;

    @ManyToMany(mappedBy="users")
    private List<Thread> threads;

    @ManyToMany(mappedBy = "users")
    private List<Book_Group> book_groups;

    @OneToMany(mappedBy="user")
    private List<Message> messages;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.messages = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.book_groups = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public User(String name, String password, String securityQuestion, String securityAnswer) {
        this.name = name;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.messages = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.book_groups = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    @JsonIgnore
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @JsonIgnore
    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    @JsonIgnore
    public List<Book_Group> getBook_groups() {
        return book_groups;
    }

    public void setBook_groups(List<Book_Group> book_groups) {
        this.book_groups = book_groups;
    }

    @JsonIgnore
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
