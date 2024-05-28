package ru.gb.springdemo.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueDto;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.ReaderService;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class IssueConverter {
    private final BookService bookService;
    private final ReaderService readerService;

    public IssueDto entityToDto(Issue issue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new IssueDto(
                issue.getId(),
                bookService.getBookById(issue.getBookId()).getName(),
                issue.getReaderId(),
                readerService.getReaderById(issue.getReaderId()).getName(),
                issue.getDateOfIssue().format(formatter),
                issue.getDateOfReturn() == null ? null : issue.getDateOfReturn().format(formatter));
    }
}
