package ru.gb.springdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.converters.IssueConverter;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueDto;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.service.IssueService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
  private final IssueService issueService;
  private final IssueConverter issueConverter;

  @GetMapping
  public List<IssueDto> getAllIssues() {
    return issueService.getAllIssues().stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/{issueId}")
  public IssueDto getIssueById(@PathVariable Long issueId) {
    return issueConverter.entityToDto(issueService.getIssueById(issueId));
  }

  @GetMapping("/book/{bookId}")
  public List<IssueDto> getIssuesByBook(@PathVariable Long bookId) {
    return issueService.getIssuesByBook(bookId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/reader/{readerId}")
  public List<IssueDto> getIssuesByReader(@PathVariable Long readerId) {
    return issueService.getIssuesByReader(readerId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/reader/{readerId}/current")
  public List<IssueDto> getCurrentIssuesByReader(@PathVariable Long readerId) {
    return issueService.getCurrentIssuesByReader(readerId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
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
