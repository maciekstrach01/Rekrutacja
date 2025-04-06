package com.example.github_api.controller;

import com.example.github_api.service.GitHubService;
import com.example.github_api.model.RepositoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/{username}/repositories")
    public List<RepositoryResponse> getUserRepositories(@PathVariable String username) {
        return gitHubService.getRepositories(username);
    }
}
