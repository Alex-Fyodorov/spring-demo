package ru.gb.library.auth.converters;

import org.springframework.stereotype.Component;
import ru.gb.library.api.ReaderDto;
import ru.gb.library.auth.entities.Reader;

@Component
public class ReaderConverter {

    public ReaderDto entityToDto(Reader reader) {
        return new ReaderDto(reader.getId(), reader.getName());
    }
}
