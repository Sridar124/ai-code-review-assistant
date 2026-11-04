
# AI Code Review Assistant

An AI-powered code review application built with Java and Spring Boot that analyzes source code for bugs, security vulnerabilities, performance issues, code quality, and algorithm complexity.

## Features

- AI-powered source code analysis using Groq API
- Automatic bug and performance issue detection
- Security vulnerability detection
- SQL Injection identification and remediation suggestions
- Time and space complexity analysis
- Code quality scoring
- Corrected source code generation
- Secret masking for sensitive credentials
- Rule-based code review suggestions
- Input validation and structured error responses
- Automated unit testing

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming |
| Spring Boot | REST API development |
| Maven | Dependency management |
| Groq API | AI-powered code analysis |
| Jackson | JSON processing |
| Postman | API testing |
| JUnit 5 | Unit testing |
| Mockito | Service-layer testing |
| Git & GitHub | Version control |

## Project Architecture

```text
ai-code-review/
├── src/
│   ├── main/
│   │   ├── java/com/sridar/codereview/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── rules/
│   │   │   ├── security/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/sridar/codereview/
│           ├── security/
│           └── service/
├── pom.xml
└── README.md
```

## Configuration

Requirements:
- Java 21
- Maven
- Groq API key

Set your Groq API key as an environment variable.

Windows PowerShell:

```powershell
$env:GROQ_API_KEY="your_api_key"
```

The application uses the following configuration:

```properties
spring.application.name=ai-code-review
groq.api.url=https://api.groq.com/openai/v1/chat/completions
groq.api.key=${GROQ_API_KEY}
groq.api.model=openai/gpt-oss-20b
```

Never commit your API key or other credentials to GitHub.

## Run the Application

Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Navigate to the project:

```bash
cd ai-code-review
```

Run using Maven:

```powershell
.\mvnw.cmd spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

## API Documentation

### Review Source Code

**Endpoint**

```http
POST /api/reviews
```

**Request URL**

```text
http://localhost:8080/api/reviews
```

**Request Body**

```json
{
  "language": "Java",
  "code": "public class Demo { public static void main(String[] args) { System.out.println(\"Hello World\"); } }"
}
```

**Response includes:**

- Code review summary
- Quality score
- Time and space complexity
- Detected bugs
- Performance issues
- Security findings
- Code quality observations
- Improvement suggestions
- Corrected source code

## Validation

- Language is required.
- Source code cannot be empty.
- Maximum source code length: 20,000 characters.
- Invalid requests return HTTP 400.

## Testing

Run automated tests:

```powershell
.\mvnw.cmd test
```

The project includes tests for secret masking and service-layer suggestion aggregation.

## Security

- API credentials are supplied through environment variables.
- Sensitive values are masked before AI processing.
- Known source-code secrets are additionally masked in AI output.
- SQL Injection risks are identified with remediation suggestions.
- Input validation protects the review endpoint.

Secret masking uses pattern-based detection and is not a guarantee that every possible secret format will be detected.

## Future Enhancements

- Support for additional programming languages
- More comprehensive static code analysis
- Authentication and authorization
- Code review history
- Frontend dashboard
- Cloud deployment

## Author

**Sridar L S**

GitHub: https://github.com/Sridar124
