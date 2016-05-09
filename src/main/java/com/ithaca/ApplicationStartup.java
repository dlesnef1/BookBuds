package com.ithaca;

import au.com.bytecode.opencsv.CSVReader;
import com.ithaca.book.Book;
import com.ithaca.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;


/**
 * Runs on startup, seeds the database with all of books we have.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        try {
            fileReader();
        } catch (IOException e) {
        }
    }

    public void fileReader() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("books.csv"));
        String[] nextLine;

        boolean b;
        while ((nextLine = reader.readNext()) != null) {
            b = false;
            for(String i : nextLine) {
                if (i.equals("")) {
                    b = true;
                }
            }
            if (!b) {
                String title = nextLine[0];
                String author = nextLine[1];
                String publisher = nextLine[2];
                String date = nextLine[3];
                String description = nextLine[4];
                String isbn = nextLine[5];
                String image = nextLine[6];

                bookRepository.save(new Book(title, author, publisher, isbn, date, image, description));
            }
        }
    }

}
