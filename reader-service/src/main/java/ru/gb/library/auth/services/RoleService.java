package ru.gb.library.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.library.auth.entities.Role;
import ru.gb.library.auth.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
