package com.ithaca.message;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return The thread between the two users.
     */
    @RequestMapping
    public Thread findThread(HttpServletRequest request, @RequestParam String recipient) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.findThread(id.longValue(), recipient);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param recipient The name of the other person the current user wishes to message
     * @param text The message the user will add to the thread between the current user and the recipient.
     * @return The thread between the two users with the new message added.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Thread create(HttpServletRequest request, String recipient, String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.create(id.longValue(), recipient, text);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param messageId The id of the message the user would like to edit
     * @param text The text the user would like to change the message too.
     * @return The thread of the message the user currently just edited.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Thread edit(HttpServletRequest request, @PathVariable("id") Long messageId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.edit(id.longValue(), messageId, text);
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
