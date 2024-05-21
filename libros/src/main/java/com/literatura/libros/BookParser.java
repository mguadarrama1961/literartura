package com.literatura.libros;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookParser {

    private static final Logger LOGGER = Logger.getLogger(BookParser.class.getName());

    public List<Book> parseJsonToBooks(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode resultsNode = rootNode.path("results");

            if (resultsNode.isArray()) {
                for (JsonNode bookNode : resultsNode) {
                    Book book = objectMapper.treeToValue(bookNode, Book.class);
                    books.add(book);
                }
            } else {
                LOGGER.log(Level.WARNING, "Expected an array node but got: {0}", resultsNode);
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error processing JSON: {0}", e.getMessage());
        }

        return books;
    }
}
