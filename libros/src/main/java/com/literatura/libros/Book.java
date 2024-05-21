package com.literatura.libros;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String mediaType;
    private int downloadCount;

    @ElementCollection
    private List<String> subjects;

    @ElementCollection
    private List<String> bookshelves;

    @ElementCollection
    private List<String> languages;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors;

    @ElementCollection
    private Map<String, String> formats;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMediaType() {
        return mediaType;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }
}
