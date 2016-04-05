package com.ithaca.message;

import com.ithaca.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by David on 4/5/16.
 */
@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name="thread_user", joinColumns=@JoinColumn(name="thread_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
    private Set<User> users;

    @OneToMany (mappedBy="thread", cascade=CascadeType.ALL)
    List<Message> messages;

    public Thread() {
        this.users = new HashSet<>();
        this.messages = new ArrayList<>();
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
