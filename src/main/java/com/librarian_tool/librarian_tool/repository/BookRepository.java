package com.librarian_tool.librarian_tool.repository;

import com.librarian_tool.librarian_tool.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
}
