package ru.gb.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueRequest {
  private Long readerId;
  private Long bookId;
}
