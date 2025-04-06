package com.example.github_api.client;

import com.example.github_api.exception.GitHubUserNotFoundException;
import com.example.github_api.model.GitHubBranch;
import com.example.github_api.model.GitHubRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private final RestTemplate restTemplate;

    public List<GitHubRepo> getRepositories(String username) {
        try {
            String url = "https://api.github.com/users/" + username + "/repos";
            GitHubRepo[] response = restTemplate.getForObject(url, GitHubRepo[].class);
            return Arrays.asList(response);
        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubUserNotFoundException("User not found: " + username);
        }
    }

    public List<GitHubBranch> getBranches(String username, String repoName) {
        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        GitHubBranch[] response = restTemplate.getForObject(url, GitHubBranch[].class);
        return Arrays.asList(response);
    }
}
