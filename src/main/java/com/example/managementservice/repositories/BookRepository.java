package com.example.managementservice.repositories;

import com.example.managementservice.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
