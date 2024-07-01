package ru.gb.library.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.library.api.IssueRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(TestSecurityConfigTwo.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class IssueControllerTestTwo {
    @Autowired
    private MockMvc mockMvc;

//    @PostMapping
//    public IssueDto openIssue(@RequestBody IssueRequest issueRequest,
//                              @RequestHeader("Authorization") String token)
    @Test
    public void openIssueTest() throws Exception {
        mockMvc.perform(post("/issues")
                        .content(new ObjectMapper().writeValueAsString(
                                new IssueRequest(1L, 1L)))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(16)))
                .andExpect(jsonPath("$.readerName", is("defaultUser")));
    }
}
