package com.ithaca.group;


import com.ithaca.book.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Book_GroupTest {

    @Test
    public void testBookGroup() {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");
        Book_Group group = new Book_Group(book);

        Assert.assertEquals(book, group.getBook());
        Assert.assertEquals(0, group.getPosts().size());
        Assert.assertEquals(0, group.getUsers().size());
    }
}
