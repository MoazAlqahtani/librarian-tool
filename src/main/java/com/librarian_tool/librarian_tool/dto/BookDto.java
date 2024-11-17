package com.librarian_tool.librarian_tool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.librarian_tool.librarian_tool.model.book.BookCategory;
import com.librarian_tool.librarian_tool.model.book.BookModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    private Long id;
    private String isbn;
    private String name;
    private Date publishDate;
    private String author;
    private BookCategory category;
    private String message;

    public BookModel toEntity(){
        return BookModel.builder()
                .id(this.id)
                .isbn(this.isbn)
                .name(this.name)
                .publishDate(this.publishDate)
                .author(this.author)
                .category(this.category)
                .build();
    }

    public BookDto addBookDto(BookModel bookModel){
        return BookDto.builder()
                .id(bookModel.getId())
                .isbn(bookModel.getIsbn())
                .name(bookModel.getName())
                .publishDate(bookModel.getPublishDate())
                .author(bookModel.getAuthor())
                .category(bookModel.getCategory())
                .build();

    }

    public BookDto removeBookDto(String msg){
        return BookDto.builder()
                .message(this.message)
                .build();
    }

    public BookDto updateBookDto(BookModel bookModel){
        return BookDto.builder()
                .id(bookModel.getId())
                .isbn(bookModel.getIsbn())
                .name(bookModel.getName())
                .publishDate(bookModel.getPublishDate())
                .author(bookModel.getAuthor())
                .category(bookModel.getCategory())
                .build();
    }
    public static BookDto viewBooksDto(BookModel bookModel){
        return BookDto.builder()
                .id(bookModel.getId())
                .name(bookModel.getName())
                .build();
    }

    public static BookDto displayBook(BookModel bookModel){
        return BookDto.builder()
                .id(bookModel.getId())
                .isbn(bookModel.getIsbn())
                .name(bookModel.getName())
                .publishDate(bookModel.getPublishDate())
                .author(bookModel.getAuthor())
                .category(bookModel.getCategory())
                .build();
    }
}
