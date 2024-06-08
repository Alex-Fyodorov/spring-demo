package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
@Schema(name = "Читатель")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Schema(name = "Идентификатор")
  private Long id;

  @Column(name = "name")
  @Schema(name = "Имя")
  private String name;

  public Reader(String name) {
    this.name = name;
  }
}
