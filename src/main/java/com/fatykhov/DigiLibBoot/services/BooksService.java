package com.fatykhov.DigiLibBoot.services;

import com.fatykhov.DigiLibBoot.models.Book;
import com.fatykhov.DigiLibBoot.models.Person;
import com.fatykhov.DigiLibBoot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll(String sortByYear) {
        if (Boolean.parseBoolean(sortByYear))
            return booksRepository.findAll(Sort.by("bookPublicationYear"));
        else if (sortByYear == null)
            return booksRepository.findAll();
        else
            return booksRepository.findAll();
    }

    public List<Book> findAll(int pageNum, int itemsPerPage, String sortByYear) {
        if (Boolean.parseBoolean(sortByYear))
            return booksRepository.findAll
                    (PageRequest.of(pageNum, itemsPerPage, Sort.by("bookPublicationYear")))
                    .getContent();
        else if (sortByYear == null)
            return booksRepository.findAll(PageRequest.of(pageNum, itemsPerPage)).getContent();
        else
            return booksRepository.findAll(PageRequest.of(pageNum, itemsPerPage)).getContent();
    }

    public Book findOne(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        return foundBook.orElse(null);
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findBooksByBookTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int bookId, Book updatedBook) {
        updatedBook.setBookId(bookId);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    @Transactional
    public void freeBook(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        if (foundBook.isPresent()) {
            Person owner = foundBook.get().getOwner();
            owner.getBooks().remove(foundBook);

            foundBook.get().setOwner(null);
            foundBook.get().setAssignedAt(null);
        }
    }

    @Transactional
    public void assignBook(Person person, int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setOwner(person);
            book.setAssignedAt(new Date());
        }
    }
}
