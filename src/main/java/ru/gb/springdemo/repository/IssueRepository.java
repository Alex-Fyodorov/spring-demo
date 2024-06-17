package ru.gb.library.repository;

import org.springframework.stereotype.Repository;
import ru.gb.library.entities.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

}
