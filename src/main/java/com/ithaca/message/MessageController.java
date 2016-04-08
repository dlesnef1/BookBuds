package com.ithaca.message;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by David on 4/4/16.
 */

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @RequestMapping
    public Thread findThread(HttpServletRequest request, @RequestParam String otherName) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.findThread(id.longValue(), otherName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Thread create(HttpServletRequest request, String recipient, String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.create(id.longValue(), recipient, text);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Thread edit(HttpServletRequest request, @PathVariable("id") Long messageId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.edit(id.longValue(), messageId, text);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(HttpServletRequest request, @PathVariable("id") Long messageId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return messageServiceImpl.delete(id.longValue(), messageId);
    }
}
