package com.example.managementservice.bootstrap;

import com.example.managementservice.model.Book;
import com.example.managementservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
* Adding 2 book examples at service startup
* */

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BootStrapData(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        Book book1 = new Book("Design Patterns",
                "1234-5678-90",
                "1994",
                "700",
                "Hard Cover");

        Book book2 = new Book("Atomic Habits",
                "4321-8765-09",
                "2018",
                "360",
                "Soft Cover");

        // persist book objects
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
