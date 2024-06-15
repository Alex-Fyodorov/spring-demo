package ru.gb.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.converters.ReaderConverter;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.model.ReaderDto;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
@Tag(name = "Readers")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderConverter readerConverter;
    @GetMapping
    @Operation(summary = "get all readers",
            description = "Загружает из базы данных полный список читателей.")
    public List<ReaderDto> getAllReaders() {
        return readerService.getAllReaders().stream()
                .map(readerConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{readerId}")
    @Operation(summary = "get one reader",
            description = "Загружает из базы данных читателя, идентификатор которого указан. " +
                    "Если такого в базе нет, выдаёт ошибку.")
    public ReaderDto getReaderById(@PathVariable Long readerId) {
        return readerConverter.entityToDto(readerService.getReaderById(readerId));
    }

    @PostMapping
    @Operation(summary = "add new reader",
            description = "Сохраняет в базе данных нового пользователя.")
    public ReaderDto addReader(@RequestBody Reader reader) {
        return readerConverter.entityToDto(readerService.addNewReader(reader));
    }

//    @PutMapping
//    @Operation(summary = "update reader",
//            description = "Изменяет в базе данные пользователя.")
//    public ReaderDto updateReader(@RequestBody Reader reader) {
//        return readerConverter.entityToDto(readerService.updateReader(reader));
//    }

    @DeleteMapping("/{readerId}")
    @Operation(summary = "delete reader",
            description = "Удаляет из базы данных читателя, идентификатор которого указан. " +
                    "Если такого в базе нет, выдаёт ошибку.")
    public ResponseEntity<?> deleteReader(@PathVariable Long readerId) {
        readerService.deleteReader(readerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
