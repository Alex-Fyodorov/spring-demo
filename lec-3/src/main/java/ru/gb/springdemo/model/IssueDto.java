package ru.gb.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long id;
    private String bookName;
    private Long readerId;
    private String readerName;
    private String dateOfIssue;
    private String dateOfReturn;
}
