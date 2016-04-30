package com.ithaca.group;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by David on 4/28/16.
 */

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @RequestMapping(method = RequestMethod.POST)
    public Book_Group create(HttpServletRequest request, @RequestParam Long bookId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.create(id.longValue(), bookId);
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Book_Group join(HttpServletRequest request, @RequestParam Long groupId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.join(id.longValue(), groupId);
    }
}
