package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
      new Book("Клиффорд Саймак", "Заповедник гоблинов"),
      new Book("Фёдор Достоевский", "Преступление и наказание"),
      new Book("William Shakespeare", "Romeo and Juliet")
    ));
  }

  public Optional<Book> getBookById(long id) {
    return books.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst();
  }

  public List<Book> getAllBooks() {
    return books;
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public boolean deleteBook(Long id) {
    return books.removeIf(book -> book.getId().equals(id));
  }
}
