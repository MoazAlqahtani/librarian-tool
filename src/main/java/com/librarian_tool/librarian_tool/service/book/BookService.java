package com.librarian_tool.librarian_tool.service.book;


import com.librarian_tool.librarian_tool.dto.BookDto;
import com.librarian_tool.librarian_tool.model.book.BookModel;
import com.librarian_tool.librarian_tool.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService implements BookServiceInterface {

    @Autowired
    private final BookRepository bookRepository;
    public BookDto createBook(BookDto bookDto){
        BookModel book = bookRepository.save(bookDto.toEntity());
        return bookDto.addBookDto(book);
    }

    public BookDto removeBook(BookDto bookDto){
        try {
            bookRepository.deleteById(bookDto.getId());
            bookDto.setMessage("Book "+ bookDto.getId() +" has been deleted");
        }catch (Exception e){
            bookDto.setMessage(e.getMessage());
        }
        return bookDto.removeBookDto(bookDto.getMessage());
    }

    public List<BookDto> getAllBooks(){
        List<BookModel> books = bookRepository.findAll();
        return books.stream().map(BookDto::viewBooksDto).toList();
    }

    public BookDto updateBook(BookDto bookDto){
        BookModel book = bookRepository.findById(bookDto.getId()).orElseThrow();
        bookRepository.save(bookDto.toEntity());
        return bookDto.updateBookDto(book);
    }

    public BookDto displayBook(Long id){
        BookModel book = bookRepository.findById(id).orElseThrow();
        return BookDto.displayBook(book);
    }






}
