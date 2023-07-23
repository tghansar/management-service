package com.example.managementservice.controllers;

import com.example.managementservice.models.Book;
import com.example.managementservice.models.Books;
import com.example.managementservice.repositories.BookRepository;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "books")
@Path("/books")
public class BookRESTController {

    private final BookRepository bookRepository;

    public BookRESTController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GET
    @Produces("application/json")
    public Books getAllBooks() {
        Books books = new Books();
        books.setBooks(new ArrayList<Book>(
                (Collection<? extends Book>) bookRepository.findAll()));

        return books;
    }

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createBook(Book book) throws URISyntaxException {
        if (book.getName() == null
                || book.getIsbn() == null
                || book.getPublishDate() == null
                || book.getPrice() == null
                || book.getBookType() == null) {
            return Response.status(400).entity("Please provide all inputs").build();
        }

        book.setId(bookRepository.findAll().spliterator().getExactSizeIfKnown() + 1);
        book.setUri("/book-management/" + book.getId());

        // Save to H2 DB
        bookRepository.save(book);

        return Response.status(201).contentLocation(new URI(book.getUri())).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBookById(@PathParam("id") Long id) throws URISyntaxException {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return Response
                    .status(200)
                    .entity(book)
                    .contentLocation(new URI("/book-management/" + id)).build();
        }
        else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateBook(@PathParam("id") Long id, Book book) {

        Optional<Book> optionalBook = bookRepository.findById(id);
        Book temp;

        if (optionalBook.isPresent()) {
            temp = optionalBook.get();
        }
        else {
            return Response.status(404).build();
        }

        temp.setName(book.getName());
        temp.setIsbn(book.getIsbn());
        temp.setPublishDate(book.getPublishDate());
        temp.setPrice(book.getPrice());
        temp.setBookType(book.getBookType());

        bookRepository.save(temp);

        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return Response.status(200).build();
        }
        else {
            return Response.status(404).build();
        }
    }
}
