package com.book.BookCatalogue.assembler;

import com.book.BookCatalogue.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookAssembler {

    public Book toBookEntity(Book book){
        Book newBook = new Book();
        newBook.setBookTitle(book.getBookTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setIsbn(book.getIsbn());
        newBook.setpDate(book.getpDate());
        return newBook;
    }
}
