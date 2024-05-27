package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ReaderRepository {
  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new Reader("Michael Miller"),
      new Reader("Karl Schmitt"),
      new Reader("Keiko Sato")
    ));
  }

  public Optional<Reader> getReaderById(long readerId) {
    return readers.stream()
            .filter(it -> Objects.equals(it.getId(), readerId))
            .findFirst();
  }

  public List<Reader> getAllReaders() {
    return readers;
  }

  public void addReader(Reader reader) {
    readers.add(reader);
  }

  public boolean deleteReader(Long id) {
    return readers.removeIf(r -> r.getId().equals(id));
  }
}
