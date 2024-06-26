package ru.gb.library.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.library.auth.entities.Reader;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Optional<Reader> findByName(String name);
}
