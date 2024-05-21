package com.literatura.libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/load")
    public List<Book> loadBooksFromJson(@RequestBody String jsonString) {
        List<Book> books = bookService.parseJsonToBooks(jsonString);
        return bookService.saveAllBooks(books);
    }
}
