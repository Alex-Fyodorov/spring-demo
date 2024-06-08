package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Форма заказа для фронта")
public class IssueDto {
    @Schema(name = "Идентификатор заказа")
    private Long id;
    @Schema(name = "Название книги")
    private String bookName;
    @Schema(name = "Идентификатор читателя")
    private Long readerId;
    @Schema(name = "Имя читателя")
    private String readerName;
    @Schema(name = "Дата открытия заказа")
    private String dateOfIssue;
    @Schema(name = "Дата закрытия заказа")
    private String dateOfReturn;
}
