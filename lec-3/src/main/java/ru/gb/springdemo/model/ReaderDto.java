package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Форма читателя для фронта")
public class ReaderDto {
    @Schema(name = "Идентификатор читателя")
    private Long id;
    @Schema(name = "Имя читателя")
    private String name;
}
