package ru.gb.library.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long id;
    private String bookName;
    private String readerName;
    private String dateOfIssue;
    private String dateOfReturn;
}
