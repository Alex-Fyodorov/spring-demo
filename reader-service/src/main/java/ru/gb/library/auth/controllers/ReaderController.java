package ru.gb.library.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.library.api.ReaderDto;
import ru.gb.library.auth.converters.ReaderConverter;
import ru.gb.library.auth.services.ReaderService;
import ru.gb.library.auth.entities.Reader;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderConverter readerConverter;

    @GetMapping
    public List<ReaderDto> getAllReaders() {
        return readerService.getAllReaders().stream()
                .map(readerConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{readerId}")
    public ReaderDto getReaderById(@PathVariable Long readerId) {
        return readerConverter.entityToDto(readerService.getReaderById(readerId));
    }

    @GetMapping("/name/{readerName}")
    public ReaderDto getReaderByName(@PathVariable String readerName) {
        return readerConverter.entityToDto(readerService.getReaderByName(readerName));
    }

    @PostMapping
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
    public ResponseEntity<?> deleteReader(@PathVariable Long readerId) {
        readerService.deleteReader(readerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
