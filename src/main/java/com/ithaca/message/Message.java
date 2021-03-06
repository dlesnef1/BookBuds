package com.ithaca.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The message entity. A message is a personal message sent between two users. It contains text and a time created. A User is
 * associated with it, along with the Thread that it is in.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Thread thread;

    @NotNull
    private String text;

    @NotNull
    private String created;

    public Message() {
    }

    public Message(User user, Thread thread, String text, String created) {
        this.user = user;
        this.thread = thread;
        this.text = text;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getName();
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

    @JsonIgnore
    public Thread getThread() {
        return thread;
    }
}
