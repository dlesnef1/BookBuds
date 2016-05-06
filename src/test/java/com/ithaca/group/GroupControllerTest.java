package com.ithaca.group;

import com.ithaca.book.Book;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest {

    @Mock
    private GroupServiceImpl groupService;

    @InjectMocks
    private GroupController groupController;

    private HttpServletRequest request;
    private Long one;
    private Long two;
    private Book_Group group;
    private Book book;


    @Before
    public void init() {
        one = 1L;
        two = 2L;
        book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");
        group = new Book_Group(book);
    }


    @Test
    public void create() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.create(one, one)).thenReturn(group);
        when(groupService.create(one, two)).thenReturn(null);
        Assert.assertEquals(group, groupController.create(request, one).getBody());
        Assert.assertEquals(null, groupController.create(request, two).getBody());
    }

    @Test
    public void join() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.join(one, one)).thenReturn(group);
        when(groupService.join(one, two)).thenReturn(null);

        Assert.assertEquals(group, groupController.join(request, one).getBody());
        Assert.assertEquals(null, groupController.join(request, two).getBody());
    }

    @Test
    public void find() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.find(one, one)).thenReturn(group);
        when(groupService.find(one, two)).thenReturn(null);

        Assert.assertEquals(group, groupController.find(request, one).getBody());
        Assert.assertEquals(null, groupController.find(request, two).getBody());
    }

    @Test
    public void post() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.post(one, one, "post")).thenReturn(group);
        when(groupService.post(one, two, "post")).thenReturn(null);

        Assert.assertEquals(group, groupController.post(request, one, "post").getBody());
        Assert.assertEquals(null, groupController.post(request, two, "post").getBody());
    }

    @Test
    public void reply() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.reply(one, one, one, "post")).thenReturn(group);
        when(groupService.reply(one, two, one, "post")).thenReturn(null);

        Assert.assertEquals(group, groupController.reply(request, one, one, "post").getBody());
        Assert.assertEquals(null, groupController.reply(request, one, two, "post").getBody());
    }

    @Test
    public void edit() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.edit(one, one, one, "post")).thenReturn(group);
        when(groupService.edit(one, two, one, "post")).thenReturn(null);

        Assert.assertEquals(group, groupController.edit(request, one, one, "post").getBody());
        Assert.assertEquals(null, groupController.edit(request, one, two, "post").getBody());
    }

    @Test
    public void upvote() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.upvote(one, one, one)).thenReturn(group);
        when(groupService.upvote(one, two, one)).thenReturn(null);

        Assert.assertEquals(group, groupController.upvote(request, one, one).getBody());
        Assert.assertEquals(null, groupController.upvote(request, one, two).getBody());
    }

    @Test
    public void report() throws Exception {
        request = mock(HttpServletRequest.class);
        Claims claims = mock(Claims.class);
        Integer oneInt = 1;

        when(request.getAttribute("claims")).thenReturn(claims);
        when(claims.get("id")).thenReturn(oneInt);

        when(groupService.report(one, one, one)).thenReturn(group);
        when(groupService.report(one, two, one)).thenReturn(null);

        Assert.assertEquals(group, groupController.report(request, one, one).getBody());
        Assert.assertEquals(null, groupController.report(request, one, two).getBody());
    }

}