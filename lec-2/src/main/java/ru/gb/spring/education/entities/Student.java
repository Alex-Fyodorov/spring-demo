package ru.gb.spring.education.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Group group;
    private static long count = 1;

    public Student(String name, Group group) {
        this.id = count++;
        this.name = name;
        this.group = group;
    }
}
