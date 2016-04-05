package com.ithaca.message;

import com.ithaca.user.User;
import com.ithaca.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by David on 4/5/16.
 */

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public List<Message> findAll(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        }
        return user.getMessages();
    }

    public Message create(Long userId, String recipientName, String text) {
        User user = userRepository.findOne(userId);
        User recipient = userRepository.findByName(recipientName);

        if (user == null || recipient == null || user == recipient) {
            return null;
        }

        Message message = new Message(user, new Thread(), text, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date()));

        messageRepository.save(message);
        user.getMessages().add(message);
        userRepository.save(user);

        return message;
    }
}
