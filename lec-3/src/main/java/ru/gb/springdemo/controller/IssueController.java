package ru.gb.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Issues")
@RequiredArgsConstructor
public class IssueController {
  private final IssueService issueService;
  private final IssueConverter issueConverter;

  @GetMapping
  @Operation(summary = "get all issues",
          description = "Загружает из базы данных полный список заказов.")
  public List<IssueDto> getAllIssues() {
    return issueService.getAllIssues().stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/{issueId}")
  @Operation(summary = "get one issue",
          description = "Загружает из базы данных заказ, идентификатор которого указан. " +
                  "Если такого в базе нет, выдаёт ошибку.")
  public IssueDto getIssueById(@PathVariable Long issueId) {
    return issueConverter.entityToDto(issueService.getIssueById(issueId));
  }

  @GetMapping("/book/{bookId}")
  @Operation(summary = "get all issues by book id",
          description = "Загружает из базы данных все заказы книги, идентификатор которой указан. " +
                  "Если такой книги в базе нет, выдаёт ошибку.")
  public List<IssueDto> getIssuesByBook(@PathVariable Long bookId) {
    return issueService.getIssuesByBook(bookId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/reader/{readerId}")
  @Operation(summary = "get all issues by reader id",
          description = "Загружает из базы данных все заказы читателя, идентификатор которого указан. " +
                  "Если такого читателя в базе нет, выдаёт ошибку.")
  public List<IssueDto> getIssuesByReader(@PathVariable Long readerId) {
    return issueService.getIssuesByReader(readerId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/reader/{readerId}/current")
  @Operation(summary = "get current issues by reader id",
          description = "Загружает из базы данных текущие заказы читателя, идентификатор которого указан. " +
                  "Если такого читателя в базе нет, выдаёт ошибку.")
  public List<IssueDto> getCurrentIssuesByReader(@PathVariable Long readerId) {
    return issueService.getCurrentIssuesByReader(readerId).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @PostMapping
  @Operation(summary = "open issue",
          description = "Открывает новый заказ.")
  public Issue openIssue(@RequestBody IssueRequest issueRequest) {
    return issueService.openIssue(issueRequest);
  }

  @PatchMapping("/{issueId}")
  @Operation(summary = "close issue",
          description = "Вставляет текущую дату в поле закрытия заказа, закрывая таким образом заказ.")
  public Issue closeIssue(@PathVariable Long issueId) {
    return issueService.closeIssue(issueId);
  }

  @DeleteMapping("/{issueId}")
  @Operation(summary = "delete issue",
          description = "Удаляет из базы данных заказ, идентификатор которого указан. " +
                  "Если такого в базе нет, выдаёт ошибку.")
  public ResponseEntity<?> deleteIssue(@PathVariable Long issueId) {
    issueService.deleteIssue(issueId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
