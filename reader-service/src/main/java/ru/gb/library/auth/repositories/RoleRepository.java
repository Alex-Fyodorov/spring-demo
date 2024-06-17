package ru.gb.library.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.library.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
