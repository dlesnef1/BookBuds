package com.ithaca.message;

import com.ithaca.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Thread entity which is the list of message between two users.
 */
@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "thread_user", joinColumns = @JoinColumn(name = "thread_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    List<Message> messages;

    public Thread() {
        this.users = new HashSet<>();
        this.messages = new ArrayList<>();
    }

    public Set<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
