package com.ithaca.user;

import com.ithaca.group.Book_Group;
import com.ithaca.message.Thread;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * The controller that provides the end points for a user. This involves creating and accessing a user's account information.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserHelper userHelper;

    /**
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @return The account of the current user, based off of the request parameter which contains a token unique to the user. 400 if not request.
     */
    @RequestMapping("/account")
    public ResponseEntity<User> find(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        User user = userService.find(id.longValue());
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    /**
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @return The book groups for the current user. Return 400 if error.
     */
    @RequestMapping("/groups")
    public ResponseEntity<List<Book_Group>> findGroups(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        List<Book_Group> groups = userService.findGroups(id.longValue());
        if (groups == null) {
            return new ResponseEntity<List<Book_Group>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @return The threads for the current user.
     */
    @RequestMapping("/threads")
    public ResponseEntity<List<Thread>> findThreads(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        List<Thread> threads = userService.findThreads(id.longValue());
        if (threads == null) {
            return new ResponseEntity<List<Thread>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(threads);
    }

    /**
     * @param name     The name of the user, must be not already taken.
     * @param password The password for the new user, it is hashed before being stored.
     * @param question A recovery question for a user, this will be asked if the user wants recover their password
     * @param answer   The answer to the recovery question provided by the user.
     * @return Return a Map with key "token" and value of the token associated with the user. This is unique and will
     * be  allow the user to be identifiable for future requests.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> create(String name, String password, String question, String answer) {

        User user = userService.create(name, password, question, answer);
        if (user == null) {
            return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userHelper.generateToken(user));
    }

    /**
     * @param name     The name of the user wishing to log in.
     * @param password The password for the user.
     * @return Return a Map with key "token" and value of the token associated with the user. This is unique and will
     * be  allow the user to be identifiable for future requests.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> login(String name, String password) {

        User user = userService.checkValid(name, password);
        if (user == null) {
            return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userHelper.generateToken(user));
    }

    /**
     * @param name The name of the account the person wants to change the password of
     * @return The question of the associated answer. This will is so the user knows the security question they are meant to answer.
     */
    @RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> showQuestion(@RequestParam String name) {

        Map<String, String> question = userService.showQuestion(name);
        if (question == null) {
            return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(question);
    }

    /**
     * @param name        The name of the user that wishes to change their password.
     * @param newPassword The user's desired new password.
     * @param answer      The answer to the user's security question.
     * @return Return a Map with key "token" and value of the token associated with the user. This is unique and will
     * be  allow the user to be identifiable for future requests.
     */
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> changePassword(String name, String newPassword, String answer) {
        User user = userService.changePassword(name, newPassword, answer);
        if (user == null) {
            return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userHelper.generateToken(user));
    }
}
