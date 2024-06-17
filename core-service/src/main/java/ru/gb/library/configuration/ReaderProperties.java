package ru.gb.library.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.reader")
public class ReaderProperties {
    private int maxAllowedBooks = 1;
}
