package ru.gb.springdemo.service;

import jakarta.transaction.Transactional;
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
        return readerRepository.findAll();
    }

    public Reader getReaderById(Long readerId) {
        return readerRepository.findById(readerId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Reader not found. id: %d", readerId)));
    }

    public Reader addNewReader(Reader reader) {
        reader.setId(null);
        return readerRepository.save(reader);
    }

    @Transactional
    public Reader updateReader(Reader inReader) {
        Reader reader = getReaderById(inReader.getId());
        reader.setName(inReader.getName());
        return readerRepository.save(reader);
    }

    @Transactional
    public void deleteReader(Long id) {
        Reader reader = getReaderById(id);
        readerRepository.delete(reader);
    }
}
