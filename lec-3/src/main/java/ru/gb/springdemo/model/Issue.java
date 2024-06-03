package ru.gb.springdemo.model;

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
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "reader_id")
  private Reader reader;

  @Column(name = "date_of_issue")
  @CreationTimestamp
  private LocalDateTime dateOfIssue;

  @Column(name = "date_of_return")
  @Timestamp
  private LocalDateTime dateOfReturn;

  public Issue(Book book, Reader reader) {
    this.book = book;
    this.reader = reader;
  }
}
