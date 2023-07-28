package com.example.managementservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(String name, String isbn, String publishDate, String price, String bookType) {
        this.name = name;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.price = price;
        this.bookType = bookType;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name="uri")
    private String uri;

    @XmlElement(name = "book_name")
    private String name;

    @XmlElement(name = "book_isbn")
    private String isbn;

    @XmlElement(name = "book_publish_dt")
    private String publishDate;

    @XmlElement(name = "book_price")
    private String price;

    @XmlElement(name = "book_type")
    private String bookType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", price='" + price + '\'' +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}