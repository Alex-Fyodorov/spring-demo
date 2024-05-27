package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Book not found. id: %d", id)));
    }

    public Book addNewBook(Book inBook) {
        Book book = new Book(inBook.getAuthor(), inBook.getName());
        bookRepository.addBook(book);
        return book;
    }

    public Book updateBook(Book inBook) {
        Book book = getBookById(inBook.getId());
        book.setAuthor(inBook.getAuthor());
        book.setName(inBook.getName());
        return book;
    }

    public boolean deleteBook(Long id) {
        return bookRepository.deleteBook(id);
    }
}
