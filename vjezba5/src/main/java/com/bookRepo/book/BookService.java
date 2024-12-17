package com.bookRepo.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        System.out.println(book);
        Optional<Book> BookByTitle = bookRepository.findByTitle(book.getTitle());
        if (BookByTitle.isPresent()) {
            throw new IllegalStateException("Book already exists");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with the id " + bookId +" does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
        new IllegalStateException("Book with the id " + bookId + " does not exist"));
        if (title != null && !title.isEmpty() && !Objects.equals(book.getTitle(), title)) {
            Optional<Book> BookByTitle = bookRepository.findByTitle(title);
            if (BookByTitle.isPresent()) {
                throw new IllegalStateException("Book already exists");
            }
            book.setTitle(title);
        }
        if (author != null && !author.isEmpty() && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }
    }


}
