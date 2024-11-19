package com.librarian_tool.librarian_tool.book;


import com.librarian_tool.librarian_tool.dto.BookDto;
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

    /**
     * Creates a new book and saves it in the database.
     *
     * @param bookDto The book data transfer object.
     * @return The created BookDto.
     */
    public BookDto createBook(BookDto bookDto){
        BookModel book = bookRepository.save(bookDto.toEntity());
        return bookDto.addBookDto(book);
    }

    /**
     * Removes a book by its ID.
     *
     * @param bookDto The book data transfer object containing the book ID.
     * @return The updated BookDto with a message.
     */
    public BookDto removeBook(BookDto bookDto){
        try {
            bookRepository.deleteById(bookDto.getId());
            bookDto.setMessage("Book "+ bookDto.getId() +" has been deleted");
        }catch (Exception e){
            bookDto.setMessage(e.getMessage());
        }
        return bookDto.removeBookDto(bookDto.getMessage());
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list of BookDtos.
     */
    public List<BookDto> getAllBooks(){
        List<BookModel> books = bookRepository.findAll();
        return books.stream().map(BookDto::viewBooksDto).toList();
    }

    /**
     * Updates an existing book with the provided information.
     *
     * @param bookDto The updated book data transfer object.
     * @return The updated BookDto.
     */
    public BookDto updateBook(BookDto bookDto){
        BookModel book = bookRepository.findById(bookDto.getId()).orElseThrow();
        bookRepository.save(bookDto.toEntity());
        return bookDto.updateBookDto(book);
    }

    /**
     * Displays a single book by its ID.
     *
     * @param id The ID of the book to display.
     * @return The BookDto for the found book.
     */
    public BookDto displayBook(Long id){
        BookModel book = bookRepository.findById(id).orElseThrow();
        return BookDto.displayBook(book);
    }






}
