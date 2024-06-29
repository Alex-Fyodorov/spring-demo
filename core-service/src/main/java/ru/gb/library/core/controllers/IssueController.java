package ru.gb.library.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.library.api.IssueDto;
import ru.gb.library.api.IssueRequest;
import ru.gb.library.core.converters.IssueConverter;

import ru.gb.library.core.services.IssueService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/issues")
@Tag(name = "Issues")
@RequiredArgsConstructor
@CrossOrigin("*")
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

  @GetMapping("/reader")
  @Operation(summary = "get all issues by reader id",
          description = "Загружает из базы данных все заказы читателя, идентификатор которого указан. " +
                  "Если такого читателя в базе нет, выдаёт ошибку.")
  public List<IssueDto> getIssuesByReader(@RequestParam("reader_name") String readerName) {
    return issueService.getIssuesByReader(readerName).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/reader/current")
  @Operation(summary = "get current issues by reader id",
          description = "Загружает из базы данных текущие заказы читателя, идентификатор которого указан. " +
                  "Если такого читателя в базе нет, выдаёт ошибку.")
  public List<IssueDto> getCurrentIssuesByReader(@RequestParam("reader_name") String readerName) {
    return issueService.getCurrentIssuesByReader(readerName).stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
  }

  @PostMapping
  @Operation(summary = "open issue",
          description = "Открывает новый заказ.")
  public IssueDto openIssue(@RequestBody IssueRequest issueRequest,
                            @RequestHeader("Authorization") String token) {
    return issueConverter.entityToDto(issueService.openIssue(issueRequest, token));
  }

  @PatchMapping("/{issueId}")
  @Operation(summary = "close issue",
          description = "Вставляет текущую дату в поле закрытия заказа, закрывая таким образом заказ.")
  public IssueDto closeIssue(@PathVariable Long issueId) {
    return issueConverter.entityToDto(issueService.closeIssue(issueId));
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
