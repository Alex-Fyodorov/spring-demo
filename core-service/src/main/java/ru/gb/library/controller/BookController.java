package ru.gb.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.library.entities.Book;
import ru.gb.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Books")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "get all books",
            description = "Загружает из базы данных полный список книг.")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "get one book",
            description = "Загружает из базы данных книгу, идентификатор которой указан. " +
                    "Если такой в базе нет, выдаёт ошибку.")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    @Operation(summary = "add new book",
            description = "Сохраняет в базе данных новою книгу.")
    public Book addBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @PutMapping
    @Operation(summary = "update book",
            description = "Изменяет в базе данные книги.")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{bookId}")
    @Operation(summary = "delete book",
            description = "Удаляет из базы данных книгу, идентификатор которой указан. " +
                    "Если такой в базе нет, выдаёт ошибку.")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
