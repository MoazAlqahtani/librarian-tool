package com.librarian_tool.librarian_tool.book;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Extends {@link JpaRepository} to provide CRUD operations and custom query support.
 */
public interface BookRepository extends JpaRepository<BookModel, Long> {
}
