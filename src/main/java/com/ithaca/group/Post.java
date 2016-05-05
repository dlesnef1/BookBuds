package com.ithaca.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * The post entity. Used for adding a text-based post to a group. Contains text, time created, score, if it's reported, along
 * with parent post and children posts under it, and the user/bookgroup it is associated with.
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

    @NotNull
    private Integer score;

    @NotNull
    private Reported reported;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post parent;

    @OneToMany(mappedBy = "parent")
    private List<Post> children;

    public Post() {
    }

    public Post(User user, Book_Group bookGroup, String text, String created, Post parent) {
        this.bookGroup = bookGroup;
        this.user = user;
        this.text = text;
        this.created = created;
        this.score = 0;
        this.reported = Reported.SAFE;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Reported getReported() {
        return reported;
    }

    public void setReported(Reported reported) {
        this.reported = reported;
    }

    @JsonIgnore
    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public List<Post> getChildren() {
        return children;
    }

    public void setChildren(List<Post> children) {
        this.children = children;
    }
}
