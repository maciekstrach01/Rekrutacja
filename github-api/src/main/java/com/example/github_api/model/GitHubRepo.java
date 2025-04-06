package com.example.github_api.model;

public record GitHubRepo(String name, Owner owner, boolean fork) {
    public record Owner(String login) {}
}
