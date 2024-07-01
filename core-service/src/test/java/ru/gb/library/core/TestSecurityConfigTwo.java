package ru.gb.library.core;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.gb.library.api.ReaderDto;
import ru.gb.library.core.configurations.JwtTokenUtil;
import ru.gb.library.core.integrations.ReaderServiceIntegration;

import java.util.List;

@TestConfiguration
public class TestSecurityConfigTwo {

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil() {
            public String getUsernameFromToken(String token) {
                return "defaultUser";
            }

            public List<String> getRolesFromToken(String token) {
                return List.of("ROLE_ADMIN");
            }
        };
    }

    @Bean
    public ReaderServiceIntegration readerServiceIntegration() {
        return new ReaderServiceIntegration(null, null) {
            public ReaderDto getReaderById(Long id, String token) {
                return new ReaderDto(id, "defaultUser");
            }
        };
    }
}
