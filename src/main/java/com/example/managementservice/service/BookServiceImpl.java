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
    Book mergeFields (Book current, Book other) {

        current.setName((other.getName() == null
                || "".equals(other.getName())) ? current.getName() : other.getName());

        current.setIsbn((other.getIsbn() == null
                || "".equals(other.getIsbn())) ? current.getIsbn() : other.getIsbn());

        current.setPublishDate((other.getPublishDate() == null
                || "".equals(other.getPublishDate())) ? current.getPublishDate() : other.getPublishDate());

        current.setPrice((other.getPrice() == null
                || "".equals(other.getPrice())) ? current.getPrice() : other.getPrice());

        current.setBookType((other.getBookType() == null
                || "".equals(other.getBookType())) ? current.getBookType() : other.getBookType());

        return current;
    }
}
