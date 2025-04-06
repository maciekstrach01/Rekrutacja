package com.example.github_api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GitHubApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Value("${github.token}")
    private String githubToken;

    @Test
    void shouldReturnRepositoriesForExistingUser() throws Exception {
        mockMvc.perform(get("/api/github/octocat/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturn404ForNonExistentUser() throws Exception {
        mockMvc.perform(get("/api/github/someNonExistingUsername123456/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("User not found")));
    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoRepositories() throws Exception {
        mockMvc.perform(get("/api/github/emptyUser/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnRepositoriesExcludingForks() throws Exception {
        mockMvc.perform(get("/api/github/someUser/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.fork == false)].repositoryName").exists())
                .andExpect(jsonPath("$[?(@.fork == false)].ownerLogin").value("someUser"))
                .andExpect(jsonPath("$[?(@.fork == false)].branches").isArray());
    }

    @Test
    void shouldReturnRepositoryDataCorrectly() throws Exception {
        mockMvc.perform(get("/api/github/octocat/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.repositoryName == 'Hello-World')].repositoryName").value("Hello-World"))
                .andExpect(jsonPath("$[?(@.repositoryName == 'Hello-World')].ownerLogin").value("octocat"));
    }

    @Test
    void shouldReturn404ForNonExistentRepository() throws Exception {
        mockMvc.perform(get("/api/github/octocat/nonexistentRepo/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("Not Found")));
    }

    @Test
    void shouldHandleInvalidJsonFormatGracefully() throws Exception {
        mockMvc.perform(get("/api/github/invalidUser/repositories")
                        .header("Authorization", githubToken))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("User not found")));
    }

}
