package ru.gb.library.auth.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.library.auth.entities.Reader;
import ru.gb.library.auth.entities.Role;
import ru.gb.library.auth.repositories.ReaderRepository;


import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService implements UserDetailsService {
    private final ReaderRepository readerRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(Long readerId) {
        return readerRepository.findById(readerId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. id: %d", readerId)));
    }

    public Reader getReaderByName(String readerName) {
        return readerRepository.findByName(readerName)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. name: %s", readerName)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws NoSuchElementException {
        Reader reader = readerRepository.findByName(username)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. name: %s", username)));
        return new org.springframework.security.core.userdetails.User(
                username, reader.getPassword(), getAuthority(reader.getRoles()));
    }

    public Collection<? extends GrantedAuthority> getAuthority(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public Reader addNewReader(Reader reader) {
        reader.setId(null);
        Role role = roleService.findById(1L)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. id: %d", 1L)));
        reader.setRoles(List.of(role));
        reader.setPassword(bCryptPasswordEncoder.encode(reader.getPassword()));
        return readerRepository.save(reader);
    }

//    @Transactional
//    public Reader updateReader(Reader inReader) {
//        Reader reader = getReaderById(inReader.getId());
//        reader.setName(inReader.getName());
//        return readerRepository.save(reader);
//    }

    @Transactional
    public void deleteReader(Long id) {
        Reader reader = getReaderById(id);
        readerRepository.delete(reader);
    }
}
