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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookSearch bookSearch;

    @InjectMocks
    private BookServiceImpl bookService;

    private String title;
    private List<Book> books;
    private List<Book_Group> groups;
    private Book_Group group;
    private Book book;
    private Long one;
    private Long two;

    @Before
    public void init() {
        title = "To Kill a Mockingbird";
        book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");
        books = new ArrayList<>();
        books.add(book);

        groups = new ArrayList<>();
        group = new Book_Group(book);
        groups.add(group);
        book.getBook_groups().add(group);

        one = 1L;
        two = 2L;
    }

    @Test
    public void search() throws Exception {
        when(bookSearch.search(title)).thenReturn(books);
        Assert.assertEquals(books, bookService.search(title));
    }

    @Test
    public void findBook() throws Exception {
        when(bookRepository.findOne(one)).thenReturn(book);
        when(bookRepository.findOne(two)).thenReturn(null);

        Assert.assertEquals(book, bookService.findBook(one));
        Assert.assertEquals(null, bookService.findBook(two));
    }

    @Test
    public void findGroups() throws Exception {
        when(bookRepository.findOne(one)).thenReturn(book);
        when(bookRepository.findOne(two)).thenReturn(null);

        Assert.assertEquals(groups, bookService.findGroups(one));
        Assert.assertEquals(null, bookService.findGroups(two));
    }

}