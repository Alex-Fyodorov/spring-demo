package ru.gb.springdemo.converters;

import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.model.ReaderDto;

@Component
public class ReaderConverter {

    public ReaderDto entityToDto(Reader reader) {
        return new ReaderDto(reader.getId(), reader.getName());
    }
}
