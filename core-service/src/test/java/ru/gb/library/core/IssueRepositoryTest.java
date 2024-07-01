package ru.gb.library.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.library.core.entities.Issue;
import ru.gb.library.core.repositories.IssueRepository;

import java.util.List;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class IssueRepositoryTest {
    @Autowired
    private IssueRepository issueRepository;
//    @Autowired
//    private TestEntityManager testEntityManager;

    @Test
    public void findByBookIdTest() {
        List<Issue> issueList = issueRepository.findByBookId(1L);
        Assertions.assertEquals(3, issueList.size());
        for (Issue issue : issueList) {
            Assertions.assertEquals("Заповедник гоблинов", issue.getBook().getName());
        }
    }

    @Test
    public void findByReaderTest() {
        List<Issue> issueList = issueRepository.findByReader("Ryosuke Yamada");
        Assertions.assertEquals(5, issueList.size());
        for (Issue issue : issueList) {
            Assertions.assertEquals("Ryosuke Yamada", issue.getReader());
        }
    }
}
