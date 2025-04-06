# GitHub API Integration using Java 21 and Spring Boot

This project is a simple Spring Boot application that integrates with the GitHub API. The goal is to retrieve information about a user's public repositories, including repository names, owner logins, branch names, and the latest commit SHAs. It filters out forked repositories and handles error cases gracefully, providing meaningful error messages in case of issues such as a non-existent user.

The project demonstrates the use of RESTful web services, exception handling, and interaction with external APIs. It also includes integration tests to verify the correctness of the implementation.

## Capabilities

- List Repositories: Retrieves all public repositories of a specified GitHub user that are not forks.

- Repository Details: For each repository, fetches branch names and the latest commit SHAs.

- Error Handling: Returns a 404 error response with a descriptive message for non-existent users.

- Integration Tests: Verifies the correctness of the API responses for various test cases, such as existing users, non-existing users, and repositories.

- No Pagination: The solution does not use pagination for API responses (as per the task requirements).

- No Authentication Required: The application does not require authentication for accessing GitHub’s public API.

## Screenshots

![Image](https://github.com/user-attachments/assets/40810c5c-331c-42f5-88a9-d98a6d2a32ba)

## 🛠 Skills

- Java 21: Utilizes the latest version of Java for building a robust and scalable backend.

- Spring Boot: Leverages Spring Boot for building RESTful services, handling web requests, and managing dependencies.

- RESTful API Integration: Integrates with GitHub’s public API to fetch repository data.

- Exception Handling: Implements global exception handling for clean error responses.

- Unit and Integration Testing: Uses JUnit and MockMvc for testing API endpoints and ensuring the correctness of the application logic.

## How to run this app

Prerequisites

- Java 21

- Maven

- GitHub Token

1. Clone this repository

```
git clone https://github.com/maciekstrach01/Rekrutacja.git
cd Rekrutacja

```

2. Configure GitHub Token: You can provide a GitHub token by adding it to the application.properties file

3. Build the project

```
mvn clean install
```

4. Run the application

```
mvn spring-boot:run
```
