package com.book.BookCatalogue.repository;

import com.book.BookCatalogue.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryCustom {

}
