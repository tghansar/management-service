package com.example.managementservice.controllers;

import com.example.managementservice.models.Book;
import com.example.managementservice.models.Books;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "books")
@Path("/books")
public class BookRESTController
{
    private static Map<Long, Book> DB = new HashMap<>();

    @GET
    @Produces("application/json")
    public Books getAllBooks()
    {
        Books books = new Books();
        books.setBooks(new ArrayList<Book>(DB.values()));
        return books;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createBook(Book book) throws URISyntaxException
    {
        if(book.getName() == null
                || book.getIsbn() == null
                || book.getPublishDate() == null
                || book.getPrice() == null
                || book.getBookType() == null)
        {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        System.out.println(book.toString());
        book.setId(Long.valueOf(DB.values().size() + 1));
        book.setUri("/book-management/"+book.getId());
        DB.put(book.getId(), book);
        return Response.status(201).contentLocation(new URI(book.getUri())).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBookById(@PathParam("id") Long id) throws URISyntaxException
    {
        Book book = DB.get(id);

        if(book == null)
        {
            return Response.status(404).build();
        }

        return Response
                .status(200)
                .entity(book)
                .contentLocation(new URI("/book-management/"+id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateBook(@PathParam("id") Long id, Book book) throws URISyntaxException
    {
        Book temp = DB.get(id);
        if(book == null)
        {
            return Response.status(404).build();
        }

        temp.setName(book.getName());
        temp.setIsbn(book.getIsbn());
        temp.setPublishDate(book.getPublishDate());
        temp.setPrice(book.getPrice());
        temp.setBookType(book.getBookType());

        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) throws URISyntaxException
    {
        Book book = DB.get(id);

        if(book != null)
        {
            DB.remove(book.getId());
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    static
    {
        Book book1 = new Book("Design Patterns",
                "1234-5678-90",
                "1994",
                "700",
                "Hard Cover");
        book1.setId(1L);

        Book book2 = new Book("Atomic Habits",
                "4321-8765-09",
                "2018",
                "360",
                "Soft Cover");
        book2.setId(2L);

        DB.put(book1.getId(), book1);
        DB.put(book2.getId(), book2);
    }
}
