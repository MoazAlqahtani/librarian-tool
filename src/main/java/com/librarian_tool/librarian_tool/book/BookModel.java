package com.librarian_tool.librarian_tool.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //@Column(nullable = false)
    private String isbn;
    //@Column(nullable = false)
    private String name;
    //@Column(nullable = false)
    private Date publishDate;
    //@Column(nullable = false)
    private String author;
    @Enumerated(EnumType.STRING)
    private BookCategory category;


}

