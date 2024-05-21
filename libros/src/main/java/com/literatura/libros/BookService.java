package com.literatura.libros;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookService {
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> saveAllBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public List<Book> parseJsonToBooks(String jsonString) {
        List<Book> books = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode resultsNode = rootNode.path("results");
            if (resultsNode.isArray()) {
                for (JsonNode bookNode : resultsNode) {
                    Book book = objectMapper.treeToValue(bookNode, Book.class);
                    books.add(book);
                }
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error processing JSON", e);
        }
        return books;
    }
}
