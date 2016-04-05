package com.ithaca.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ithaca.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by David on 4/4/16.
 */

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @NotNull
    private String text;

    @NotNull
    private String created;

    public Message() {
    }

    public Message(User user, String text, String created) {
        this.user = user;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }
}
