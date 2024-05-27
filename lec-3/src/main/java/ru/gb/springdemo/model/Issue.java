package ru.gb.springdemo.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
public class Issue {
  private final Long id;
  private final Long bookId;
  private final Long readerId;
  private final LocalDateTime dateOfIssue;
  private LocalDateTime dateOfReturn;
  public static Long sequence = 1L;

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.dateOfIssue = LocalDateTime.now();
  }
}
