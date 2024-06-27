package ru.gb.aspect;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.slf4j.event.Level;

@Data
@ConfigurationProperties("starter-aspect.properties")
public class LoggingProperties {

    /**
     * Настроить уровень логирования.
     */
    private Level logLevel = Level.DEBUG;
}
