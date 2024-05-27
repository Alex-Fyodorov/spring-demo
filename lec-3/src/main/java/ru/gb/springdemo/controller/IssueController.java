package ru.gb.springdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.service.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
  private final IssueService issueService;

  @GetMapping
  public List<Issue> getAllIssues() {
    return issueService.getAllIssues();
  }

  @GetMapping("/{issueId}")
  public Issue getIssueById(@PathVariable Long issueId) {
    return issueService.getIssueById(issueId);
  }

  @GetMapping("/book/{bookId}")
  public List<Issue> getIssuesByBook(@PathVariable Long bookId) {
    return issueService.getIssuesByBook(bookId);
  }

  @GetMapping("/reader/{readerId}")
  public List<Issue> getIssuesByReader(@PathVariable Long readerId) {
    return issueService.getIssuesByReader(readerId);
  }

  @GetMapping("/reader/{readerId}/current")
  public List<Issue> getCurrentIssuesByReader(@PathVariable Long readerId) {
    return issueService.getCurrentIssuesByReader(readerId);
  }

  @PostMapping
  public Issue openIssue(@RequestBody IssueRequest issueRequest) {
    return issueService.openIssue(issueRequest);
  }

  @PatchMapping("/{issueId}")
  public Issue closeIssue(@PathVariable Long issueId) {
    return issueService.closeIssue(issueId);
  }

  @DeleteMapping("/{issueId}")
  public ResponseEntity<?> deleteIssue(@PathVariable Long issueId) {
    if (issueService.deleteIssue(issueId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
