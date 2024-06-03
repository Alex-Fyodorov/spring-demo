package ru.gb.springdemo.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueDto;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class IssueConverter {

    public IssueDto entityToDto(Issue issue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new IssueDto(
                issue.getId(),
                issue.getBook().getName(),
                issue.getReader().getId(),
                issue.getReader().getName(),
                issue.getDateOfIssue().format(formatter),
                issue.getDateOfReturn() == null ? null : issue.getDateOfReturn().format(formatter));
    }
}
