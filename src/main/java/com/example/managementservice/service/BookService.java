package com.example.managementservice.service;

import com.example.managementservice.model.Book;

import java.util.Optional;

public interface BookService {

    Iterable<Book> findAll();

    Optional<Book> findById(Long id);

    void save(Book book);

    boolean update(Book book, Long id);

    void deleteById(Long id);
}
