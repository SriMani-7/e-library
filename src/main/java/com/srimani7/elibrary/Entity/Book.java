package com.srimani7.elibrary.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "author"}, name = "unique_book_author_title_constraint")
})
public class Book {
    @Id
    private String isbn;
    private String title;
    private String publisher;
    private String author;
    @Column(name = "published_year")
    private int publishedYear;
    private String genre;
}
