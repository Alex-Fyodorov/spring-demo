package ru.gb.spring.education.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.spring.education.entities.Group;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    private final List<Group> groups;

    public GroupRepository() {
        this.groups = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        groups.add(new Group("Chemistry"));
        groups.add(new Group("Physics"));
        groups.add(new Group("Math"));
    }

    public Group findById(Long id) {
        return groups.stream().filter(group -> group.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Group with id = %d not found.\n", id)));
    }

    public Group findByName(String name) {
        return groups.stream().filter(group -> group.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Group with name = %s not found.\n", name)));
    }
}
