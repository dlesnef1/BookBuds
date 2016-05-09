package com.ithaca.book;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    @Test
    public void testBook() {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin", "9015431231", "1965", ".com", "required for school");

        Assert.assertEquals("To Kill a Mockingbird", book.getTitle());
        Assert.assertEquals("Harper Lee", book.getAuthor());
        Assert.assertEquals("Penguin", book.getPublisher());
        Assert.assertEquals("9015431231", book.getIsbn());
        Assert.assertEquals("1965", book.getDate());
        Assert.assertEquals(".com", book.getImageUrl());
        Assert.assertEquals("required for school", book.getDescription());
    }


}
