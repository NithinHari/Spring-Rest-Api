package com.book.BookCatalogue.service;

import com.book.BookCatalogue.exception.CustomBookException;
import com.book.BookCatalogue.model.Book;
import com.book.BookCatalogue.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        List<Book> book = new ArrayList<>();
        bookRepository.findAll().forEach(book::add);
        return book;
    }

    @Override
    public Book addBook(Book bookRecord) {
      if(bookRecord != null && bookRecord.getBookTitle() != null){
          return bookRepository.save(bookRecord);
      }else{
          throw new CustomBookException("Name cannot be null");
      }
    }

    @Override
    public Book updateBook(Book bookRecord) {
        return bookRepository.save(bookRecord);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getFilteredBooks(String title, Long isbn) {
        return bookRepository.findBookByCriteria(title, isbn);
    }
}
