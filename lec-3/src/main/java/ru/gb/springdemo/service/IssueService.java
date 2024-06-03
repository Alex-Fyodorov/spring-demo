package ru.gb.springdemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {
  private final BookService bookService;
  private final ReaderService readerService;
  private final IssueRepository issueRepository;

  @Value("${application.max-allowed-books:1}")
  private int maxBooksCount;

  public List<Issue> getAllIssues() {
    return issueRepository.findAll();
  }

  public Issue getIssueById(Long issueId) {
    return issueRepository.findById(issueId)
            .orElseThrow(() -> new NoSuchElementException(
                    String.format("Issue not found. id: %d", issueId)));
  }

  public List<Issue> getIssuesByBook(Long bookId) {
    return issueRepository.findByBookId(bookId);
  }

  public List<Issue> getIssuesByReader(Long readerId) {
    return issueRepository.findByReaderId(readerId);
  }

  public List<Issue> getCurrentIssuesByReader(Long readerId) {
    return getIssuesByReader(readerId).stream()
            .filter(issue -> issue.getDateOfReturn() == null)
            .collect(Collectors.toList());
  }

  @Transactional
  public Issue openIssue(IssueRequest issueRequest) {
    Book book = bookService.getBookById(issueRequest.getBookId());
    Reader reader = readerService.getReaderById(issueRequest.getReaderId());
    int issueCount = getCurrentIssuesByReader(reader.getId()).size();
    if (issueCount >= maxBooksCount) {
      throw new IllegalStateException("The book limit has been exceeded.");
    }
    Issue issue = new Issue(book, reader);
    return issueRepository.save(issue);
  }

  @Transactional
  public Issue closeIssue(Long issueId){
    Issue issue = getIssueById(issueId);
    issue.setDateOfReturn(LocalDateTime.now());
    return issueRepository.save(issue);
  }

  @Transactional
  public void deleteIssue(Long issueId) {
    Issue issue = getIssueById(issueId);
    issueRepository.delete(issue);
  }
}
