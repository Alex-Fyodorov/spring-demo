package ru.gb.spring.education.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Group {
    private Long id;
    private String name;
    private static long count = 1;

    public Group(String name) {
        this.id = count++;
        this.name = name;
    }
}
