package br.com.fatec.booksManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private String genre;

    private String author;

    private String publisher;

    @Column(name = "PUBLICATION_YEAR")
    private String publicationYear;

    private String volume;

    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    private String isbn;

}
