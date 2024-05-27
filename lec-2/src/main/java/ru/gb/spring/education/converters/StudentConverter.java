package ru.gb.spring.education.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.spring.education.dtos.StudentDto;
import ru.gb.spring.education.entities.Student;
import ru.gb.spring.education.repositories.GroupRepository;

@Component
@RequiredArgsConstructor
public class StudentConverter {
    private final GroupRepository groupRepository;

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(),
                student.getName(),
                student.getGroup().getName());
    }

    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(),
                studentDto.getName(),
                groupRepository.findByName(studentDto.getGroup()));
    }
}
