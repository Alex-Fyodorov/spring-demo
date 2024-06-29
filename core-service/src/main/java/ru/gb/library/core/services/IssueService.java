package ru.gb.library.core.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.gb.library.api.IssueRequest;
import ru.gb.library.api.ReaderDto;
import ru.gb.library.core.configurations.ReaderProperties;
import ru.gb.library.core.entities.Book;
import ru.gb.library.core.entities.Issue;

import ru.gb.library.core.integrations.ReaderServiceIntegration;
import ru.gb.library.core.repositories.IssueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(ReaderProperties.class)
public class IssueService {
  private final BookService bookService;
  private final ReaderServiceIntegration readerService;
  private final IssueRepository issueRepository;
  private final ReaderProperties readerProperties;

//  @Value("${application.reader.max-allowed-books:1}")
//  private int maxBooksCount;

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

  public List<Issue> getIssuesByReader(String readerName) {
    return issueRepository.findByReader(readerName);
  }

  public List<Issue> getCurrentIssuesByReader(String readerName) {
    return getIssuesByReader(readerName).stream()
            .filter(issue -> issue.getDateOfReturn() == null)
            .collect(Collectors.toList());
  }

  @Transactional
  public Issue openIssue(IssueRequest issueRequest, String token) {
    Book book = bookService.getBookById(issueRequest.getBookId());
    ReaderDto reader = readerService.getReaderById(issueRequest.getReaderId(), token);
    int issueCount = getCurrentIssuesByReader(reader.getName()).size();
    if (issueCount >= readerProperties.getMaxAllowedBooks()) {
      throw new IllegalStateException("The book limit has been exceeded.");
    }
    Issue issue = new Issue(book, reader.getName());
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
