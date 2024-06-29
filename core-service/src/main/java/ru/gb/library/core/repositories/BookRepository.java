package ru.gb.library.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.library.core.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
