package com.ithaca.group;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The group controller provides endpoints for accessing groups. A group is an individual book group for a specific book.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param bookId The id for the book a user wishes to make a group for.
     * @return The newly created book group.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Book_Group create(HttpServletRequest request, @RequestParam Long bookId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.create(id.longValue(), bookId);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user wishes to join.
     * @return The book group the user joined with this user now in the group's members.
     */
    @RequestMapping(value = "/{groupId}/join", method = RequestMethod.POST)
    public Book_Group join(HttpServletRequest request, @PathVariable Long groupId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.join(id.longValue(), groupId);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user wishes to see.
     * @return the group the user wishes to see.
     */
    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public Book_Group find(HttpServletRequest request, @PathVariable Long groupId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.find(id.longValue(), groupId);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user wishes to post in, note the user must be in the group to post.
     * @param text The message the user intends to send.
     * @return The entire book group with the new message the user posted in it.
     */
    @RequestMapping(value = "/{groupId}", method = RequestMethod.POST)
    public Book_Group post(HttpServletRequest request, @PathVariable Long groupId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.post(id.longValue(), groupId, text);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user wishes to reply to a post in.
     * @param postId The id of the post the user hopes to reply too. This will be the parent of the reply.
     * @param text The actual message
     * @return The entire group with the newly added reply to a post also in the group.
     */
    @RequestMapping(value = "/{groupId}/{postId}", method = RequestMethod.POST)
    public Book_Group reply(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.reply(id.longValue(), groupId, postId, text);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user would like to edit a post in.
     * @param postId The id of the post the user is editing.
     * @param text The new text for the message, that is to say what the text of the post is being changed too.
     * @return The book group that the post is in.
     */
    @RequestMapping(value = "/{groupId}/{postId}", method = RequestMethod.PUT)
    public Book_Group edit(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId, @RequestParam String text) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.edit(id.longValue(), groupId, postId, text);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group the user wishes to upvote a post in
     * @param postId The actual post the user wishes to upvote
     * @return The group that contains the post the user is upvoting.
     */
    @RequestMapping(value = "/{groupId}/{postId}/upvote", method = RequestMethod.PUT)
    public Book_Group upvote(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.upvote(id.longValue(), groupId, postId);
    }

    /**
     *
     * @param request This is in the header of the HTTP request and is used for authenticating a user.
     * @param groupId The id of the group that contains the post the user wishes to report.
     * @param postId The id of the post the user wishes to report
     * @return The group that contains the post the user reported.
     */
    @RequestMapping(value = "/{groupId}/{postId}/report", method = RequestMethod.PUT)
    public Book_Group report(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long postId) {
        Claims claims = (Claims) request.getAttribute("claims");
        Integer id = (Integer) claims.get("id");

        return groupService.report(id.longValue(), groupId, postId);
    }
}
