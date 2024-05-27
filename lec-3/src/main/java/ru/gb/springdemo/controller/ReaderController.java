package ru.gb.springdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{readerId}")
    public Reader getReaderById(@PathVariable Long readerId) {
        return readerService.getReaderById(readerId);
    }

    @PostMapping
    public Reader addReader(@RequestBody Reader reader) {
        System.out.println(reader);
        return readerService.addNewReader(reader);
    }

    @PutMapping
    public Reader updateReader(@RequestBody Reader reader) {
        return readerService.updateReader(reader);
    }

    @DeleteMapping("/{readerId}")
    public ResponseEntity<?> deleteReader(@PathVariable Long readerId) {
        if (readerService.deleteReader(readerId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
