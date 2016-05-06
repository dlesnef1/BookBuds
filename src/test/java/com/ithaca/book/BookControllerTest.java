package com.ithaca.book;

import com.ithaca.group.Book_Group;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;
    private List<Book> books;
    private List<Book_Group> groups;
    private Book_Group group;
    private Long one;
    private Long two;

    @Before
    public void init() {
        one = 1L;
        two = 2L;

        books = new ArrayList<>();
        book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");
        books.add(book);

        group = new Book_Group(book);
        groups = new ArrayList<>();
        groups.add(group);

    }

    @Test
    public void testSearch() {
        when(bookService.search("To Kill a Mockingbird")).thenReturn(books);
        Assert.assertEquals(books, bookController.search("To Kill a Mockingbird"));
    }

    @Test
    public void testFind() {
        when(bookService.findBook(one)).thenReturn(book);
        when(bookService.findBook(two)).thenReturn(null);

        Assert.assertEquals(book, bookController.findBook(one).getBody());
        Assert.assertEquals(null, bookController.findBook(two).getBody());
    }

    @Test
    public void testFindGroups() {
        when(bookService.findGroups(one)).thenReturn(groups);
        when(bookService.findGroups(two)).thenReturn(null);

        Assert.assertEquals(groups, bookController.findGroups(one).getBody());
        Assert.assertEquals(null, bookController.findGroups(two).getBody());
    }
}
