package com.book.BookCatalogue.service;

import com.book.BookCatalogue.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();

    public Book addBook(Book bookRecord);

    public Book updateBook(Book bookRecord);

    public void deleteById(Long id);

    public List<Book> getFilteredBooks(String title, Long isbn);




}
