package ru.gb.spring.education.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.education.converters.StudentConverter;
import ru.gb.spring.education.dtos.StudentDto;
import ru.gb.spring.education.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentConverter.entityToDto(studentRepository.findById(id));
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentRepository.getAll().stream()
                .map(studentConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<StudentDto> findByNamePath(@RequestParam(name = "name") String namePath) {
        return studentRepository.findByNamePath(namePath).stream()
                .map(studentConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/group/{groupName}")
    public List<StudentDto> findByGroupName(@PathVariable String groupName) {
        return studentRepository.findByGroupName(groupName).stream()
                .map(studentConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        return studentConverter.entityToDto(studentRepository
                .saveStudent(studentConverter.dtoToEntity(studentDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (studentRepository.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
