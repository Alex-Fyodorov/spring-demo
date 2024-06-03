package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "author")
  private String author;

  @Column(name = "name")
  private String name;

  public Book(String author, String name) {
    this.author = author;
    this.name = name;
  }
}
