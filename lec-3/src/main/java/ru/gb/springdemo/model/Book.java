package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
  private final Long id;
  private String author;
  private String name;
  public static long sequence = 1L;

  public Book(String author, String name) {
    this.id = sequence++;
    this.author = author;
    this.name = name;
  }
}
