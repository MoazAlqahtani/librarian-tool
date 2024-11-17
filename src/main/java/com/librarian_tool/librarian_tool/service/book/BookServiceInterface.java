package com.librarian_tool.librarian_tool.service.book;

import com.librarian_tool.librarian_tool.dto.BookDto;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

public interface BookServiceInterface {


    BookDto createBook(BookDto bookDto);

    BookDto removeBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto bookDto);

    BookDto displayBook(Long id);
}
