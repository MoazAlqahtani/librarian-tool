package com.librarian_tool.librarian_tool.book;

import com.librarian_tool.librarian_tool.dto.BookDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for managing book-related operations.
 *
 */
@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Builder
@Slf4j
public class BookController {

    private final BookServiceInterface bookServiceInterface;

    /**
     * Endpoint to add a new book.
     *
     * @param bookDto the {@link BookDto} object containing book details.
     * @return the created {@link BookDto} and {@link HttpStatus#CREATED}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @PostMapping(value = "/add")
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto){
        log.info("Adding Book.");
        return new ResponseEntity<>(bookServiceInterface.createBook(bookDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint to remove a book.
     *
     * @param bookDto the {@link BookDto} object identifying the book to remove.
     * @return the removed {@link BookDto} and {@link HttpStatus#OK}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @PostMapping(value = "/remove")
    public ResponseEntity<BookDto> removeBook(@RequestBody BookDto bookDto){
        log.info("Removing books.");
        return new ResponseEntity<>(bookServiceInterface.removeBook(bookDto),HttpStatus.OK);
    }

    /**
     * Endpoint to fetch all books.
     *
     * @return the list of all {@link BookDto} objects and {@link HttpStatus#ACCEPTED}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @GetMapping(value = "/all-books")
    public ResponseEntity<List<BookDto>> getAllbooks(){
        log.info("Getting all books.");
        return new ResponseEntity<>(bookServiceInterface.getAllBooks().stream().toList(),HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint to update book details.
     *
     * @param bookDto the {@link BookDto} object containing updated book details.
     * @return the updated {@link BookDto} and {@link HttpStatus#OK}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @PutMapping(value = "/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        log.info("Updating book.");
        return new ResponseEntity<>(bookServiceInterface.updateBook(bookDto),HttpStatus.OK);
    }

    /**
     * Endpoint to display a book by its ID.
     *
     * @param id the ID of the book to display.
     * @return the {@link BookDto} of the requested book and {@link HttpStatus#OK}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @GetMapping(value = "/display/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id){
        log.info("Updating book.");
        return new ResponseEntity<>(bookServiceInterface.displayBook(id),HttpStatus.OK);
    }

}
