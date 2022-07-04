package com.book.BookCatalogue.repository;

import com.book.BookCatalogue.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    public List<Book> findBookByCriteria(String title, Long isbn);
}
