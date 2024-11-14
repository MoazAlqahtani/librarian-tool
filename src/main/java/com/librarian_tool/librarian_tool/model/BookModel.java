package com.librarian_tool.librarian_tool.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;
    @Column(nullable = false)
    private String ISBN;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date publishDate;
    @Column(nullable = false)
    private String author;
    @Enumerated(EnumType.STRING)
    private Category category;
}

enum Category{
    DRAMA,
    HISTORY,
    Advanture
}
