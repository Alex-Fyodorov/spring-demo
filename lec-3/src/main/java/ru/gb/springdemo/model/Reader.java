package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

  @Column(name = "password")
  @Schema(name = "Пароль")
  private String password;

  @ManyToMany
  @JoinTable(name = "readers_roles",
          joinColumns = @JoinColumn(name = "reader_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;
}
