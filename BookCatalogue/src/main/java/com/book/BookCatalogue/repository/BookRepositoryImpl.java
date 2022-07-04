package com.book.BookCatalogue.repository;

import com.book.BookCatalogue.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Book> findBookByCriteria(String title, Long isbn) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if(title != null){
            predicates.add(cb.like(book.get("title"),"%"+title+"%"));
        }
        if(isbn != null){
            predicates.add(cb.like(book.get("isbn"),"%"+isbn+"%"));
        }
        Predicate predicateForGrade = cb.and(predicates.toArray(new Predicate[0]));
        cq.where(predicateForGrade);

        return entityManager.createQuery(cq).getResultList();
    }
}
