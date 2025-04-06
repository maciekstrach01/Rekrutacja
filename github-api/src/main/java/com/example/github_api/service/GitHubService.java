package com.example.github_api.service;

import com.example.github_api.client.GitHubClient;
import com.example.github_api.model.BranchResponse;
import com.example.github_api.model.GitHubRepo;
import com.example.github_api.model.RepositoryResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubClient gitHubClient;

    public List<RepositoryResponse> getRepositories(String username) {
        List<GitHubRepo> repos = gitHubClient.getRepositories(username);

        return repos.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> new RepositoryResponse(
                        repo.name(),
                        repo.owner().login(),
                        gitHubClient.getBranches(username, repo.name()).stream()
                                .map(branch -> new BranchResponse(branch.name(), branch.commit().sha()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
