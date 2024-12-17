package com.bookRepo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }
    @DeleteMapping(path = "{Id}")
    public void deleteBook(@PathVariable("Id") Long Id) {
        bookService.deleteBook(Id);
    }

    @PutMapping(path = "{Id}")
    public void updateBook(@PathVariable("Id") Long Id, @RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        bookService.updateBook(Id,title,author);
    }

}
