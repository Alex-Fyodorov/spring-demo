package ru.gb.spring.education.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.spring.education.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private final List<Student> students;
    private final GroupRepository groupRepository;

    public StudentRepository(GroupRepository groupRepository) {
        this.students = new ArrayList<>();
        this.groupRepository = groupRepository;
    }

    @PostConstruct
    public void init() {
        students.add(new Student("Marilyn Monroe", groupRepository.findById(1L)));
        students.add(new Student("Ginger Rogers", groupRepository.findById(2L)));
        students.add(new Student("Gregory Peck", groupRepository.findById(3L)));
        students.add(new Student("Marlon Brando", groupRepository.findById(1L)));
        students.add(new Student("James Cagney", groupRepository.findById(1L)));
        students.add(new Student("James Stewart", groupRepository.findById(3L)));
    }

    public Student findById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Student with id = %d not found.\n", id)));
    }

    public List<Student> getAll() {
        return students;
    }

    public List<Student> findByNamePath(String namePath) {
        return students.stream().filter(s -> s.getName().toLowerCase().contains(namePath.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> findByGroupName(String groupName) {
        return students.stream().filter(s -> s.getGroup().getName().equals(groupName))
                .collect(Collectors.toList());
    }

    public Student saveStudent(Student student) {
        try {
            Student existingStudent = findById(student.getId());
            existingStudent.setName(student.getName());
            existingStudent.setGroup(student.getGroup());
            return existingStudent;
        } catch (Exception e) {
            Student newStudent = new Student(student.getName(), student.getGroup());
            students.add(newStudent);
            return newStudent;
        }
    }

    public boolean deleteById(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
