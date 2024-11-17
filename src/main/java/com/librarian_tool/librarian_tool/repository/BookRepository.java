package com.librarian_tool.librarian_tool.repository;

import com.librarian_tool.librarian_tool.model.book.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
