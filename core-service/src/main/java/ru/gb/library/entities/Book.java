package ru.gb.library.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@Schema(name = "Книга")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Schema(name = "Идентификатор")
  private Long id;

  @Column(name = "author")
  @Schema(name = "Автор")
  private String author;

  @Column(name = "name")
  @Schema(name = "Название книги")
  private String name;

  public Book(String author, String name) {
    this.author = author;
    this.name = name;
  }
}
