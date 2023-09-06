package com.fatykhov.DigiLibBoot.repositories;

import com.fatykhov.DigiLibBoot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByBookTitleStartingWith(String query);
}
