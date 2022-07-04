package com.book.BookCatalogue.controller;


import com.book.BookCatalogue.assembler.BookAssembler;
import com.book.BookCatalogue.exception.CustomBookException;
import com.book.BookCatalogue.exception.NoSuchBookExistsException;
import com.book.BookCatalogue.model.Book;
import com.book.BookCatalogue.repository.BookRepository;
import com.book.BookCatalogue.service.BookService;
import com.book.BookCatalogue.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAssembler assembler;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id){
        Book bookObj = getBookRec(id);

        if(bookObj != null){
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }else{
            throw new NoSuchBookExistsException("No Such book exists");
        }
    }

    private Book getBookRec(long id) {
        Optional<Book> bookObj = bookRepository.findById(id);
        if(bookObj.isPresent()){
            return bookObj.get();
        }
        return null;
    }

    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book book1 = assembler.toBookEntity(book);
        System.out.println("Ass1 "+ book1.getBookTitle());
        if(book.getBookTitle() != null && !book.getBookTitle().equalsIgnoreCase(" ")){

            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);

        }else{
            throw new CustomBookException("Book name cannot be null");
        }
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getFilteredBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long isbn
    ){
        return new ResponseEntity<>(bookService.getFilteredBooks(title, isbn), HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook
        (@PathVariable("id") final Long id, @RequestBody final Book bookRecord){
        Book book = getBookRec(id);
        if(book != null){

            bookRecord.setId(id);
            return new ResponseEntity<>(bookService.updateBook(bookRecord), HttpStatus.OK);
        }else{
            throw new NoSuchBookExistsException("No such Book exists");
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") long id){
        Book book2 = getBookRec(id);
        if(book2 != null){
            bookService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new NoSuchBookExistsException("No such Book Exists");
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks(){
        try{
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
