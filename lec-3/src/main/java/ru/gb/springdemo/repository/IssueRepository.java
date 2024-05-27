package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public List<Issue> getAllIssues() {
    return issues;
  }

  public Optional<Issue> getIssueById(long issueId) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getId(), issueId))
            .findFirst();
  }

  public List<Issue> getIssueByBook(Long bookId) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getBookId(), bookId))
            .collect(Collectors.toList());
  }

  public List<Issue> getIssueByReader(long readerId) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getReaderId(), readerId))
            .collect(Collectors.toList());
  }

  public void addIssue(Issue issue) {
    issues.add(issue);
  }

  public boolean deleteIssue(Long id) {
    return issues.removeIf(issue -> issue.getId().equals(id));
  }
}
