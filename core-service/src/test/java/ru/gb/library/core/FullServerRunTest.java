package ru.gb.library.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.library.api.IssueDto;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getAllIssuesTest() {
        List<IssueDto> issueDtoList = testRestTemplate.getForObject(
                "/issues", List.class);
        Assertions.assertThat(issueDtoList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(15);
    }
}
