package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@Schema(name = "Заказ")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Schema(name = "Идентификатор")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id")
  @Schema(name = "Книга")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "reader_id")
  @Schema(name = "Читатель")
  private Reader reader;

  @Column(name = "date_of_issue")
  @CreationTimestamp
  @Schema(name = "Дата открытия заказа")
  private LocalDateTime dateOfIssue;

  @Column(name = "date_of_return")
  @Timestamp
  @Schema(name = "Дата закрытия заказа")
  private LocalDateTime dateOfReturn;

  public Issue(Book book, Reader reader) {
    this.book = book;
    this.reader = reader;
  }
}
