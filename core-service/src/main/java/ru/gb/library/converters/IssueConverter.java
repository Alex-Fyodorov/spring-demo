package ru.gb.library.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.library.api.IssueDto;
import ru.gb.library.entities.Issue;


import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class IssueConverter {

    public IssueDto entityToDto(Issue issue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new IssueDto(
                issue.getId(),
                issue.getBook().getName(),
                issue.getReader(),
                issue.getDateOfIssue().format(formatter),
                issue.getDateOfReturn() == null ? null : issue.getDateOfReturn().format(formatter));
    }
}
