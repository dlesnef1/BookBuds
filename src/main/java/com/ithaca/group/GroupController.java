package com.ithaca.group;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{groupId}/join", method = RequestMethod.POST)
    public Book_Group join(HttpServletRequest request, @PathVariable Long groupId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.join(id.longValue(), groupId);
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public Book_Group find(HttpServletRequest request, @PathVariable Long groupId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.find(id.longValue(), groupId);
    }

    @RequestMapping(value = "/{groupId}/post", method = RequestMethod.POST)
    public Book_Group post(HttpServletRequest request, @PathVariable Long groupId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.post(id.longValue(), groupId, text);
    }

    @RequestMapping(value = "/{groupId}/{postId}", method = RequestMethod.POST)
    public Book_Group reply(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.reply(id.longValue(), groupId, postId, text);
    }

    @RequestMapping(value = "/{groupId}/{postId}", method = RequestMethod.PUT)
    public Book_Group edit(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.edit(id.longValue(), groupId, postId, text);
    }
}
