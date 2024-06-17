package ru.gb.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.library.entities.Book;
import ru.gb.library.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Book not found. id: %d", id)));
    }

    public Book addNewBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Book inBook) {
        Book book = getBookById(inBook.getId());
        book.setAuthor(inBook.getAuthor());
        book.setName(inBook.getName());
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
