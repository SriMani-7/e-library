package com.srimani7.elibrary.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "author"}, name = "unique_book_author_title_constraint")
})
@NoArgsConstructor
@AllArgsConstructor
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
