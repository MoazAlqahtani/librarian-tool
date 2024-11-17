package com.librarian_tool.librarian_tool.controller;

import com.librarian_tool.librarian_tool.service.book.BookServiceInterface;
import com.librarian_tool.librarian_tool.dto.BookDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Builder
@Slf4j
public class BookController {

    private final BookServiceInterface bookServiceInterface;

    @PostMapping(value = "/add")
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto){
        log.info("Adding Book.");
        return new ResponseEntity<>(bookServiceInterface.createBook(bookDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<BookDto> removeBook(@RequestBody BookDto bookDto){
        log.info("Removing books.");
        return new ResponseEntity<>(bookServiceInterface.removeBook(bookDto),HttpStatus.OK);
    }

    @GetMapping(value = "/all-books")
    public ResponseEntity<List<BookDto>> getAllbooks(){
        log.info("Getting all books.");
        return new ResponseEntity<>(bookServiceInterface.getAllBooks().stream().toList(),HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        log.info("Updating book.");
        return new ResponseEntity<>(bookServiceInterface.updateBook(bookDto),HttpStatus.OK);
    }

    @GetMapping(value = "/display/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id){
        log.info("Updating book.");
        return new ResponseEntity<>(bookServiceInterface.displayBook(id),HttpStatus.OK);
    }

}
