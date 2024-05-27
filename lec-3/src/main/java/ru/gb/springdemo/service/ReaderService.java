package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public Reader getReaderById(Long readerId) {
        return readerRepository.getReaderById(readerId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. id: %d", readerId)));
    }

    public Reader addNewReader(Reader inReader) {
        Reader reader = new Reader(inReader.getName());
        readerRepository.addReader(reader);
        return reader;
    }

    public Reader updateReader(Reader inReader) {
        Reader reader = getReaderById(inReader.getId());
        reader.setName(inReader.getName());
        return reader;
    }

    public boolean deleteReader(Long id) {
        return readerRepository.deleteReader(id);
    }
}
