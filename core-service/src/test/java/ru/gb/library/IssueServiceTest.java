package ru.gb.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.library.api.IssueRequest;
import ru.gb.library.api.ReaderDto;
import ru.gb.library.core.configurations.ReaderProperties;
import ru.gb.library.core.entities.Book;
import ru.gb.library.core.entities.Issue;
import ru.gb.library.core.integrations.ReaderServiceIntegration;
import ru.gb.library.core.repositories.IssueRepository;
import ru.gb.library.core.services.BookService;
import ru.gb.library.core.services.IssueService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest (classes = IssueService.class)
public class IssueServiceTest {
    @Autowired
    private IssueService issueService;
    @MockBean
    private IssueRepository issueRepository;
    @MockBean
    private BookService bookService;
    @MockBean
    private ReaderServiceIntegration readerService;
    private final ReaderProperties readerProperties = Mockito.mock(ReaderProperties.class);

    @CsvSource({"0", "4", "5", "6", "10"})
    @ParameterizedTest
    public void openIssueTest(int listSize) {
        int count = 5;
        String token = "111";
        Book book = new Book("Клиффорд Саймак", "Заповедник гоблинов");
        book.setId(1L);
        Mockito.doReturn(book).when(bookService).getBookById(1L);

        Mockito.doReturn(new ReaderDto(2L, "Ryosuke Yamada"))
                .when(readerService).getReaderById(2L, token);

        Mockito.doReturn(createIssueList(listSize))
                .when(issueRepository).findByReader("Ryosuke Yamada");

        Mockito.doReturn(count).when(readerProperties).getMaxAllowedBooks();

        if (listSize < count) {
            issueService.openIssue(new IssueRequest(2L, 1L), token);
            Mockito.verify(issueRepository, Mockito.times(1))
                    .save(ArgumentMatchers.any());
        } else {
            Assertions.assertThrows(IllegalStateException.class, () -> {
                issueService.openIssue(new IssueRequest(2L, 1L), token);
            }, "The book limit has been exceeded.");
        }
    }

    private List<Issue> createIssueList(int size) {
        List<Issue> issueList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            issueList.add(new Issue());
        }
        return issueList;
    }
}
