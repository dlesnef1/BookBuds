package com.ithaca.message;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by David on 4/4/16.
 */

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @RequestMapping
    public Thread findThread(@RequestParam String otherName, HttpServletRequest request) {
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
}
