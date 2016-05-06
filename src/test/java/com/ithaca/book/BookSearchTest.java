package com.ithaca.book;

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
public class BookSearchTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookSearch bookSearch;

    private Book book;
    private Book book2;
    private List<Book> books;

    @Before
    public void init() {
        books = new ArrayList<>();
        book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");
        book2 = new Book("The Winds of Winter", "George R.R. Martin", "Penguin", "NA", "Prob never", "lol", "most epic book ever to never happen");
        books.add(book);
        books.add(book2);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
    }

    @Test
    public void search() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);
        Assert.assertEquals("To Kill a Mockingbird", bookSearch.search("To Kill a MockingBird").get(0).getTitle());
    }

}