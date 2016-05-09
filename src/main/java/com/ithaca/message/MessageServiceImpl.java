package com.ithaca.message;

import com.ithaca.user.User;
import com.ithaca.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ThreadRepository threadRepository;

    @Override
    public Thread findThread(Long userId, String otherUserName) {
        User user = userRepository.findOne(userId);
        User otherUser = userRepository.findByName(otherUserName);

        if (user == null || otherUser == null || user == otherUser) {
            return null;
        }

        for (Thread thread : user.getThreads()) {
            if (thread.getUsers().contains(otherUser)) {
                return thread;
            }
        }

        return null;
    }

    @Override
    public Thread create(Long userId, String recipientName, String text) {
        User user = userRepository.findOne(userId);
        User recipient = userRepository.findByName(recipientName);

        if (user == null || recipient == null || user == recipient) {
            return null;
        }

        Thread currentThread = null;
        for (Thread thread : user.getThreads()) {
            if (thread.getUsers().contains(recipient)) {
                currentThread = thread;
            }
        }

        if (currentThread == null) {
            currentThread = new Thread();
            currentThread.getUsers().add(user);
            currentThread.getUsers().add(recipient);

            user.getThreads().add(currentThread);
            recipient.getThreads().add(currentThread);
        }

        Message message = new Message(user, currentThread, text,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        user.getMessages().add(message);
        currentThread.getMessages().add(message);

        userRepository.save(user);
        userRepository.save(recipient);
        threadRepository.save(currentThread);
        messageRepository.save(message);
        return currentThread;
    }

    @Override
    public Thread edit(Long userId, Long messageId, String text) {
        User user = userRepository.findOne(userId);
        Message message = messageRepository.findOne(messageId);

        if (!user.getMessages().contains(message)) {
            return null;
        }

        message.setText(text);
        messageRepository.save(message);
        return message.getThread();
    }

    @Override
    public Boolean delete(Long userId, Long messageId) {
        User user = userRepository.findOne(userId);
        Message message = messageRepository.findOne(messageId);

        if (!user.getMessages().contains(message)) {
            return false;
        }
        messageRepository.delete(message);
        return true;
    }
}
