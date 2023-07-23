package com.example.managementservice.bootstrap;

import com.example.managementservice.models.Book;
import com.example.managementservice.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        book1.setId(1L);
        book1.setUri("/book-management/1");

        Book book2 = new Book("Atomic Habits",
                "4321-8765-09",
                "2018",
                "360",
                "Soft Cover");
        book2.setId(2L);
        book2.setUri("/book-management/2");

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
