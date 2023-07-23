package com.example.managementservice.resources;

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
public class BookResource
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
        book.setId(Long.valueOf(DB.values().size() + 1));
        book.setUri("/book-management/"+book.getId());
        book.setUri("1000");
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
    public Response updateUser(@PathParam("id") Long id, Book book) throws URISyntaxException
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
    public Response deleteUser(@PathParam("id") Long id) throws URISyntaxException
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
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Design Patterns");
        book1.setIsbn("1234-5678-90");
        book1.setPublishDate("1994");
        book1.setPrice("500");
        book1.setBookType("Soft Cover");
        book1.setUri("/user-management/1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Design Patterns");
        book2.setIsbn("4321-8765-09");
        book2.setPublishDate("2010");
        book2.setPrice("1000");
        book2.setBookType("Hard Cover");
        book2.setUri("/user-management/2");

        DB.put(book1.getId(), book1);
        DB.put(book2.getId(), book2);
    }
}
