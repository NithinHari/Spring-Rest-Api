package com.book.BookCatalogue.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;


import javax.persistence.*;
import javax.validation.constraints.*;

import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Book Title cannot be empty")
    @Column(name="bookTitle")
    private String bookTitle;

    @NotEmpty(message = "Author name cannot br empty")
    @Column(name="author")
    private String author;



    //@NotEmpty(message = "ISBN cannot be empty")
    @Digits(integer=13, fraction=0)
    @Column(name="isbn")
    private Long isbn;


    @Column(name="pDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date pDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public Book(Long id, String bookTitle, String author, Long isbn, Date pDate){
        this.id=id;
        this.bookTitle=bookTitle;
        this.author=author;
        this.isbn=isbn;
        this.pDate=pDate;
    }

    public Book(){

    }

}
