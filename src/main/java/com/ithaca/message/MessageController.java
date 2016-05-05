package com.ithaca.message;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The message controller provides endpoints for messaging individual users. These are provide messages between two users.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param recipient The name of the other person the current user would like to see the thread between.
     * @return The thread between the two users. Return a 400 error if user or recipient is null.
     */
    @RequestMapping
    public ResponseEntity<Thread> findThread(HttpServletRequest request, @RequestParam String recipient) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        Thread thread = messageServiceImpl.findThread(id.longValue(), recipient);
        if (thread == null) {
            return new ResponseEntity<Thread>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(thread);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param recipient The name of the other person the current user wishes to message
     * @param text The message the user will add to the thread between the current user and the recipient.
     * @return The thread between the two users with the new message added. Return 400 if user/recip null.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Thread> create(HttpServletRequest request, String recipient, String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        Thread thread = messageServiceImpl.create(id.longValue(), recipient, text);
        if (thread == null) {
            return new ResponseEntity<Thread>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(thread);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param messageId The id of the message the user would like to edit
     * @param text The text the user would like to change the message too.
     * @return The thread of the message the user currently just edited. Return 400 error if user is null or doesn't own message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Thread> edit(HttpServletRequest request, @PathVariable("id") Long messageId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        Thread thread = messageServiceImpl.edit(id.longValue(), messageId, text);
        if (thread == null) {
            return new ResponseEntity<Thread>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(thread);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param messageId The id of the message the user would like to delete.
     * @return A boolean indicating whether the message was successfully deleted or not.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(HttpServletRequest request, @PathVariable("id") Long messageId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.delete(id.longValue(), messageId);
    }
}
