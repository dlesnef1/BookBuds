package com.ithaca.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 3/25/16.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> all() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User create(String name, String password) {
        // Hash password for security reasons.
        String securePass = BCrypt.hashpw(password, BCrypt.gensalt(12));

        return userRepository.findByName(name) == null ? userRepository.save(new User(name, securePass)) : null;
    }
}
