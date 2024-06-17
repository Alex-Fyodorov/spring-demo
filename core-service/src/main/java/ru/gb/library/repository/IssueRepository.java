package ru.gb.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.library.entities.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("select i from Issue i where i.book.id = :id")
    List<Issue> findByBookId(Long id);

    //@Query("select i from Issue i where i.reader = :name")
    List<Issue> findByReader(String name);
}
