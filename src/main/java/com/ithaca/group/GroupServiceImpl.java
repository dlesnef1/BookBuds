package com.ithaca.group;

import com.ithaca.book.Book;
import com.ithaca.book.BookRepository;
import com.ithaca.user.User;
import com.ithaca.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * Created by David on 4/28/16.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Book_Group create(Long userId, Long bookId) {
        User user = userRepository.findOne(userId);
        Book book = bookRepository.findOne(bookId);

        if (user == null || book == null) {
            return null;
        }

        Book_Group bookGroup = new Book_Group(book);
        book.getBook_groups().add(bookGroup);

        bookGroup.getUsers().add(user);
        user.getBook_groups().add(bookGroup);

        userRepository.save(user);
        bookRepository.save(book);
        groupRepository.save(bookGroup);

        return bookGroup;
    }

    @Override
    public Book_Group join(Long userId, Long groupId) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);

        if (user == null || bookGroup == null) {
            return null;
        }

        if (bookGroup.getUsers().contains(user)) {
            return bookGroup;
        }

        user.getBook_groups().add(bookGroup);
        bookGroup.getUsers().add(user);

        userRepository.save(user);
        groupRepository.save(bookGroup);

        return bookGroup;
    }

    @Override
    public Book_Group find(Long userId, Long groupId) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);

        if (user == null || bookGroup == null) {
            return null;
        }

        return bookGroup;
    }

    @Override
    public Book_Group post(Long userId, Long groupId, String text) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);

        if (user == null || bookGroup == null || !bookGroup.getUsers().contains(user)) {
            return null;
        }

        Post post = new Post(user, bookGroup, text,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()), null);

        user.getPosts().add(post);
        bookGroup.getPosts().add(post);

        userRepository.save(user);
        groupRepository.save(bookGroup);
        postRepository.save(post);

        return bookGroup;
    }

    @Override
    public Book_Group reply(Long userId, Long groupId, Long postId, String text) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);
        Post parent = postRepository.findOne(postId);

        if (user == null || bookGroup == null || parent == null) {
            return null;
        }

        Post child = new Post(user, null, text,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()), parent);
        parent.getChildren().add(child);

        user.getPosts().add(child);

        userRepository.save(user);
        groupRepository.save(bookGroup);
        postRepository.save(parent);
        postRepository.save(child);

        return bookGroup;
    }

    @Override
    public Book_Group edit(Long userId, Long groupId, Long postId, String text) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);
        Post post = postRepository.findOne(postId);

        if (user == null || bookGroup == null || post == null || !user.getPosts().contains(post)) {
            return null;
        }
        post.setText(text);

        userRepository.save(user);
        groupRepository.save(bookGroup);
        postRepository.save(post);

        return bookGroup;
    }

    @Override
    public Book_Group upvote(Long userId, Long groupId, Long postId) {
        User user = userRepository.findOne(userId);
        Book_Group bookGroup = groupRepository.findOne(groupId);
        Post post = postRepository.findOne(postId);

        if (user == null || bookGroup == null || post == null) {
            return null;
        }
        post.setScore(post.getScore() + 1);

        userRepository.save(user);
        groupRepository.save(bookGroup);
        postRepository.save(post);

        return bookGroup;
    }
}
