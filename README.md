
# AI Code Review Assistant

An AI-powered code review backend built using Java, Spring Boot, and the Groq API. It accepts source code through a REST API and generates structured code review reports with actionable recommendations.

## Features

- AI-powered source code analysis using Groq API
- Automated code quality scoring
- Potential bug identification
- Time and space complexity analysis
- Performance issue detection
- Security vulnerability analysis
- Corrected code generation
- Actionable code improvement suggestions
- Structured JSON API responses
- Rule-based checks for TODO comments and System.out.println statements
- Credential masking before sending source code to the AI service
- Request validation and error handling

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Backend Programming |
| Spring Boot | Backend Framework |
| Groq API | AI Integration |
| REST API | API Communication |
| Jackson | JSON Processing |
| Maven | Build Tool |
| Jakarta Validation | Request Validation |
| Postman | API Testing |
| Git & GitHub | Version Control |

## How It Works

1. The client submits source code and its programming language through the REST API.
2. Spring Boot validates the incoming request.
3. Rule-based checks identify TODO markers and System.out.println usage.
4. Recognized credential patterns are masked before external AI processing.
5. The Groq API analyses the submitted source code using a structured review prompt.
6. The AI response is parsed into Java response models.
7. AI-generated suggestions and rule-based suggestions are combined.
8. The application returns the final code review as a JSON response.

## Code Review Capabilities

The AI review generates:

- Code quality score
- Review summary
- Potential bugs
- Time and space complexity analysis
- Performance observations
- Security findings with severity and recommendations
- Code quality observations
- Improvement suggestions
- Corrected source code

The analysis is AI-generated and should be reviewed by developers before applying suggested changes.

## Security

- Recognizes common credential patterns such as API keys, passwords and tokens.
- Masks recognized secrets before sending submitted code to the Groq API.
- Masks recognized original secrets in generated review output.
- Treats submitted source code as untrusted input in the AI review prompt.
- Uses an environment variable for Groq API authentication.

**Note:** Secret masking is pattern-based and is not a substitute for a dedicated secrets scanner.

## API Documentation

### Code Review Endpoint

| Property | Value |
|---|---|
| HTTP Method | POST |
| Endpoint | `/api/reviews` |
| Content-Type | `application/json` |
| Server | `http://localhost:8080` |

### Sample Request

```json
{
  "language": "Java",
  "code": "public class Main { public static void main(String[] args) { System.out.println(\"Hello World\"); } }"
}
```

### Response Structure

The API returns a JSON response containing:

- `language`
- `summary`
- `qualityScore`
- `complexityAnalysis`
- `bugs`
- `performanceIssues`
- `securityFindings`
- `codeQuality`
- `suggestions`
- `correctedCode`

### Request Validation

- Language is required.
- Code is required and cannot be blank.
- Language length is limited to 50 characters.
- Source code length is limited to 20,000 characters.
- Invalid requests return HTTP 400 Bad Request.

## Installation and Setup

### Prerequisites

- Java JDK 21
- Git
- Groq API key

### Step 1: Clone the Repository

```bash
git clone https://github.com/Sridar124/ai-code-review-assistant.git

cd ai-code-review-assistant
```

### Step 2: Configure Groq API Key

Set your Groq API key as an environment variable.

For Windows PowerShell:

```powershell
$env:GROQ_API_KEY = "your_groq_api_key"
```

Keep the key private. Never commit API credentials to GitHub.

### Step 3: Run Tests

```powershell
.\mvnw.cmd test
```

### Step 4: Start the Application

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

## Testing and Verification

### Automated Tests

Latest local test execution:

| Metric | Result |
|---|---:|
| Tests run | 11 |
| Failures | 0 |
| Errors | 0 |
| Skipped | 0 |
| Build status | BUILD SUCCESS |

### Manual API Verification

The following scenarios were tested using Postman:

| Test Scenario | Result |
|---|---|
| Java code review | Passed |
| Python code review | Passed |
| SQL injection detection | Passed |
| Safe SQL query recognition | Passed |
| Complexity and performance analysis | Passed |
| HashSet optimization in corrected code | Passed |
| Hardcoded credential detection and masking | Passed |
| TODO and logging suggestions | Passed |
| Blank input validation | HTTP 400 |
| Sample prompt-injection attempt | Passed |

Java and Python were manually tested. A single Python review request returned in approximately 2.45 seconds; this is an individual observation, not a performance benchmark.

## Project Screenshots

### 1. API Review Result

![AI Code Review Result](screenshots/code-review-result.png)

### 2. Detailed Code Review

![AI Code Review Details](screenshots/code-review-details.png)

## Current Limitations

- The project currently exposes a REST API and does not include a separate frontend UI.
- AI-generated analysis may vary between requests.
- AI findings and corrected code should be independently reviewed before production use.
- The application does not automatically execute or apply generated corrected code.

## Author

**SRIDAR L S**

- GitHub: [Sridar124](https://github.com/Sridar124)
- Project Repository: [AI Code Review Assistant](https://github.com/Sridar124/ai-code-review-assistant)

---

Developed as a practical project demonstrating Java backend development, REST API design, and AI integration.
