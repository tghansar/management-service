package com.example.managementservice.service;

import com.example.managementservice.model.Book;
import com.example.managementservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public boolean update(Book newBookData, Long id) {
        Book existingBook;
        Optional<Book> optionalBook = bookRepository.findById(id);

        // retrieve valid book
        if (optionalBook.isPresent()) {
            existingBook = optionalBook.get();
            existingBook = mergeFields(existingBook, newBookData);

            // persist updated book fields
            bookRepository.save(existingBook);

            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    // -- helper methods --
    Book mergeFields (Book book1, Book book2) {
        book1.setName((book2.getName() != null) ? book2.getName() : book1.getName());
        book1.setIsbn((book2.getIsbn() != null) ? book2.getIsbn() : book1.getIsbn());
        book1.setPublishDate((book2.getPublishDate() != null) ? book2.getPublishDate() : book1.getPublishDate());
        book1.setPrice((book2.getPrice() != null) ? book2.getPrice() : book1.getPrice());
        book1.setBookType((book2.getBookType() != null) ? book2.getBookType() : book1.getBookType());

        return book1;
    }
}
