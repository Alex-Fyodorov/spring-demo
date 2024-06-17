package ru.gb.library.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@ConfigurationProperties(prefix = "integrations.reader-service")
@Data
public class ReaderServiceIntegrationProperties {
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
