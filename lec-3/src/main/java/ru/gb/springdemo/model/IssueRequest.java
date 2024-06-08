package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Заявка на открытие заказа")
public class IssueRequest {
  @Schema(name = "Идентификатор читателя")
  private Long readerId;
  @Schema(name = "Идентификатор книги")
  private Long bookId;
}
