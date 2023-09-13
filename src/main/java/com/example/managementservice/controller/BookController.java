package com.example.managementservice.controller;

import com.example.managementservice.model.Book;
import com.example.managementservice.service.BookServiceImpl;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
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
public class BookController {

    BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GET
    @Produces("application/json")
    public Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createBook(Book book) {
        if (book.getName() == null
                || book.getIsbn() == null
                || book.getPublishDate() == null
                || book.getPrice() == null
                || book.getBookType() == null) {
            return Response.status(400).entity("Please provide all inputs").build();
        }
        bookService.save(book);
        return Response.status(201).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBookById(@PathParam("id") Long id) throws URISyntaxException {
        Optional<Book> optionalBook = bookService.findById(id);

        // retrieve valid book
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return Response
                    .status(200)
                    .entity(book)
                    .contentLocation(new URI("/books/" + id)).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateBook(@PathParam("id") Long id, Book book) {
        if (bookService.update(book, id)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        Optional<Book> optionalBook = bookService.findById(id);

        // retrieve valid book
        if (optionalBook.isPresent()) {
            bookService.deleteById(id);
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}
