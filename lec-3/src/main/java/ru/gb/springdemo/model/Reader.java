package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Reader {
  private final Long id;
  private String name;
  public static Long sequence = 1L;

  public Reader(String name) {
    this.id = sequence++;
    this.name = name;
  }

  public Reader() {
    this.id = sequence++;
  }
}
