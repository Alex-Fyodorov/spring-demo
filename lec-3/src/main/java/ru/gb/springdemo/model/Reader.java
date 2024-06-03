package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  public Reader(String name) {
    this.name = name;
  }
}
