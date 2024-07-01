package ru.gb.library.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class IssueControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllIssuesTest() throws Exception {
        mockMvc.perform(get("/issues")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(15))
                .andExpect(jsonPath("$", hasSize(15)))
                .andExpect(jsonPath("$[2].readerName", is("Keiko Sato")));
    }

//    @GetMapping("/{issueId}")
//    public IssueDto getIssueById(@PathVariable Long issueId)
    @Test
    public void getIssueByIdTest() throws Exception {
        mockMvc.perform(get("/issues/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.bookName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.readerName", is("Keiko Sato")));
    }

//    @PatchMapping("/{issueId}")
//    public IssueDto closeIssue(@PathVariable Long issueId)
//    @Test
//    public void closeIssueTest() throws Exception {
//        mockMvc.perform(patch("/issues/3")
//                        .with(SecurityMockMvcRequestPostProcessors.jwt()
//                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.dateOfReturn", notNullValue()));
//    }
}
