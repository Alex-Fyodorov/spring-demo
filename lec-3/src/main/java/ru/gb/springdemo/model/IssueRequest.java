package ru.gb.springdemo.model;

import lombok.Data;

@Data
public class IssueRequest {
  private Long readerId;
  private Long bookId;
}
